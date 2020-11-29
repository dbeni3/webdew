package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CityEntity;
import hu.unideb.webdev.dao.entity.CountryEntity;
import hu.unideb.webdev.dao.entity.CustomerEntity;
import hu.unideb.webdev.dao.entity.StoreEntity;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
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
    private final StoreRepository storeRepository;
    @Override
    public void createCustomer(Customer customer)  {
        CustomerEntity customerEntity;

        customerEntity = CustomerEntity.builder()
                .store(storeRepository.findById(1))
                .firstName(customer.getFirstName())
                .lastName(customer.getLastName())
                .email(customer.getEmail())
                .address(customer.getAdress())
                .active(customer.getActive())
                .createDate(customer.getCreateDate())
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




    @Override
    public Collection<Customer> readAll() {
        return StreamSupport.stream(customerRepository.findAll().spliterator(),false)
                .map(entity -> new Customer(
                        entity.getStore().getAdress().getAddress(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getEmail(),
                        entity.getAddress().getAddress(),
                        entity.getActive(),
                        entity.getCreateDate().toString()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCustomer(Customer customer)  {
        Optional<CustomerEntity> customerEntity = StreamSupport.stream(customerRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return customer.getStore().equals("" +entity.getStore().getId()) &&
                            customer.getFirstName().equals(entity.getFirstName()) &&
                            customer.getLastName().equals(entity.getLastName()) &&
                            customer.getEmail().equals(entity.getEmail()) &&
                            customer.getAdress().equals(entity.getAddress().getAddress()) &&
                            customer.getActive().equals(entity.getActive()) &&
                            customer.getCreateDate().equals(entity.getCreateDate().toString());
                }
        ).findAny();
        if(!customerEntity.isPresent()){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        customerRepository.delete(customerEntity.get());
    }
}
