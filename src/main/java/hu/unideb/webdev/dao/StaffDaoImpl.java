package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Staff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class StaffDaoImpl implements StaffDao {

    private final StaffRepository staffRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final StoreRepository storeRepository;

    @Override
    public Collection<Staff> readAll() {
        return StreamSupport.stream(staffRepository.findAll().spliterator(),false)
                .map(entity -> new Staff(
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getAddress().getAddress(),
                        entity.getEmail(),
                        entity.getUserName(),
                        entity.getPassWord(),
                        entity.getActive(),
                        entity.getAddress().getCity().getName(),
                        entity.getAddress().getCity().getCountry().getName(),
                        entity.getStore().getId()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public void createStaff(Staff staff) throws UnknownCountryException, UnknownStoreException {
        StaffEntity staffEntity;

        staffEntity = StaffEntity.builder()
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .address(queryAddress(staff.getAddress(),queryCity(staff.getCity(),staff.getCountry())))
                .email(staff.getEmail())
                .store(queryStore(staff.getStoreId()))
                .active(staff.getActive())
                .userName(staff.getUsername())
                .passWord(staff.getPassword())
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("StaffEntity: {}", staffEntity);
        try {
            staffRepository.save(staffEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    protected  StoreEntity queryStore(int storeId) throws UnknownStoreException {
        Optional<StoreEntity> storeEntity = storeRepository.findById(storeId).stream()
                .findFirst();
        if (!storeEntity.isPresent()){
            throw new UnknownStoreException(storeEntity.get().getAddress().getAddress());
        }
        return storeEntity.get();
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
}
