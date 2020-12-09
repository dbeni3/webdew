package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.exceptions.*;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.model.Staff;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
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
    private final StoreRepository storeRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AddressRepository addressRepository;
    private final EntityManager entityManager;

    @Override
    public Collection<Staff> readAll() {
        return StreamSupport.stream(staffRepository.findAll().spliterator(),false)
                .map(entity -> new Staff(
                        entity.getStore().getId(),
                        entity.getId(),
                        entity.getFirstName(),
                        entity.getLastName(),
                        entity.getAddress().getAddress(),
                        entity.getEmail(),
                        entity.getUserName(),
                        entity.getPassWord(),
                        entity.getActive(),
                        entity.getAddress().getId(),
                        entity.getStore().getId()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void createStaff(Staff staff) throws  UnknownAddressException {
        StoreEntity store=createStore(staff.getStoreAddressId());


        StaffEntity staffEntity;
        staffEntity = StaffEntity.builder()
                .firstName(staff.getFirstName())
                .lastName(staff.getLastName())
                .address(queryAddress(staff.getStaffAddressId()))
                .email(staff.getEmail())
                .active(staff.getActive())
                .userName(staff.getUsername())
                .passWord(staff.getPassword())
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        store.setStaff(staffEntity);
        staffEntity.setStore(store);
        try {
            storeRepository.save(store);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        try {
            staffRepository.save(staffEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
        log.info("StaffEntity: {}", staffEntity);


    }
    protected StoreEntity createStore(int storeAddressId) throws UnknownAddressException {
        StoreEntity storeEntity=new StoreEntity();
        storeEntity.setAddress(queryAddress(storeAddressId));
        storeEntity.setLastUpdate(new Timestamp((new Date()).getTime()));


        return storeEntity;
    }
    protected StoreEntity queryStore(int storeId) throws UnknownStoreException {
        if (storeId<1){
            throw new UnknownStoreException("Store must be greater than 0");
        }
        StoreEntity storeEntity = entityManager.find(StoreEntity.class,storeId);
        return storeEntity;
    }
    protected AddressEntity queryAddress(int addressId) throws  UnknownAddressException {

        Optional<AddressEntity> addressEntity = addressRepository.findById(addressId).stream()
                .findFirst();

        if(!addressEntity.isPresent()){
            throw new UnknownAddressException("AddressEntity not found");
        }
        log.trace("Addres Entity: {}", addressEntity);

        return addressEntity.get();
    }
    @Override
    public void updateStaff(Staff staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException {
        Optional<StaffEntity> staffEntity=staffRepository.findById(staff.getStaffId());
        if (!staffEntity.isPresent()){
            throw new UnknownStaffException(String.format("Staff Not Found %s",staff), staff);
        }

        if (!staff.getFirstName().equals("string")){
            staffEntity.get().setFirstName(staff.getFirstName());
        }
        if (!staff.getLastName().equals("string")){
            staffEntity.get().setLastName(staff.getLastName());
        }
        if (!staff.getEmail().equals("string")){
            staffEntity.get().setEmail(staff.getEmail());
        }
        if (!staff.getUsername().equals("string")){
            staffEntity.get().setUserName(staff.getUsername());
        }
        if (!staff.getPassword().equals("string")){
            staffEntity.get().setPassWord(staff.getPassword());
        }
        if (staff.getStaffAddressId()!=(staffEntity.get().getAddress().getId())){
            staffEntity.get().setAddress(queryAddress(staff.getStaffAddressId()));
        }

        if (staff.getStoreId()!=staffEntity.get().getStore().getId()){
            staffEntity.get().setStore(queryStore(staff.getStoreId()));
        }

        staffEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        staffRepository.save(staffEntity.get());
    }
}
