package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerDaoImpl implements CustomerDao{
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final EntityManager entityManager;
    
    @Override
    public void createCustomer(Customer customer) throws UnknownStoreException, UnknownCountryException {
        CustomerEntity customerEntity;

        customerEntity = CustomerEntity.builder()
                .store(queryStore(customer.getStoreId()))
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(queryAddress(customer.getAddress(),queryCity(customer.getCity(),customer.getCountry())))
                .active(customer.getActive())
                .createDate(new Timestamp((new Date()).getTime()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("CountryEntity: {}", customerEntity);
        try {
            customerRepository.save(customerEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    protected StoreEntity queryStore(int storeId) throws UnknownStoreException {
        if (storeId<1){
            throw new UnknownStoreException("Store must be greater than 0");
        }
        StoreEntity storeEntity = entityManager.find(StoreEntity.class,storeId);
        return storeEntity;
    }

    protected CityEntity queryCity(String city, String country) throws UnknownCountryException {

        Optional<CityEntity> cityEntity = cityRepository.findByName(city).stream()
                .filter(entity -> entity.getCountry().getName().equals(country))
                .findFirst();
        if(!cityEntity.isPresent()){
            Optional<CountryEntity> countryEntity = Optional.ofNullable(countryRepository.findByName(country));
            if(!countryEntity.isPresent()){
                throw new UnknownCountryException(country);
            }
            cityEntity = Optional.ofNullable(CityEntity.builder()
                    .name(city)
                    .country(countryEntity.get())
                    .lastUpdate(new Timestamp((new Date()).getTime()))
                    .build());
            cityRepository.save(cityEntity.get());
            log.info("Recorded new City: {}, {}", city, country);
        }
        log.trace("City Entity: {}", cityEntity);
        return cityEntity.get();
    }

    protected AddressEntity queryAddress(String address ,CityEntity cityEntity) throws UnknownCountryException {

        Optional<AddressEntity> addressEntity = addressRepository.findByAddress(address).stream()
                .filter(entity -> entity.getAddress().equals(address))
                .findFirst();

        if(!addressEntity.isPresent()){
            addressEntity = Optional.ofNullable(AddressEntity.builder()
                    .address(address)
                    .city(cityEntity)
                    .lastUpdate(new Timestamp((new Date()).getTime()))
                    .build());
            addressRepository.save(addressEntity.get());
            log.info("Recorded new Addres: {}, {}", cityEntity.getName(), address);
        }
        log.trace("Addres Entity: {}", addressEntity);

        return addressEntity.get();
    }


    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(),false)
                .map(entity -> new Customer(
                        entity.getId(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getEmail(),
                        entity.getAddress().getAddress(),
                        entity.getActive(),
                        entity.getStore().getId(),
                        entity.getAddress().getCity().getName(),
                        entity.getAddress().getCity().getCountry().getName()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteCustomer(Customer customer) throws UnknownCustomerException {
        Optional<CustomerEntity> customerEntity = StreamSupport.stream(customerRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return  customer.getFirstName().equals(entity.getFirstName()) &&
                            customer.getLastName().equals(entity.getLastName()) &&
                            customer.getEmail().equals(entity.getEmail()) &&
                            customer.getAddress().equals(entity.getAddress().getAddress()) &&
                            customer.getActive().equals(entity.getActive());
                }
        ).findAny();
        if(!customerEntity.isPresent()){
            throw new UnknownCustomerException(String.format("Customer Not Found %s",customer), customer);
        }
        customerRepository.delete(customerEntity.get());
    }
    @Override
    public void updateCustomer(Customer customer) throws UnknownCustomerException, UnknownCountryException, UnknownStoreException {
        Optional<CustomerEntity> customerEntity=customerRepository.findById(customer.getCustomerId());
        if (!customerEntity.isPresent()){
            throw new UnknownCustomerException(String.format("Customer Not Found %s",customer), customer);
        }

        if (!customer.getFirstName().equals("string")){
            customerEntity.get().setFirstName(customer.getFirstName());
        }
        if (!customer.getLastName().equals("string")){
            customerEntity.get().setLastName(customer.getLastName());
        }
        if (!customer.getEmail().equals("string")){
            customerEntity.get().setEmail(customer.getEmail());
        }
        //
        if (!customer.getCity().equals("string") && !customer.getCountry().equals("string")
                && !customer.getAddress().equals("string")){
            customerEntity.get().setAddress(queryAddress(customer.getAddress(),queryCity(customer.getCity(),customer.getCountry())));
        }

        if (customer.getStoreId()!=customerEntity.get().getStore().getId()){
            customerEntity.get().setStore(queryStore(customer.getStoreId()));
        }

        customerEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        customerRepository.save(customerEntity.get());
    }
}
