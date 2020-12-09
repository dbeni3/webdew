package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
import hu.unideb.webdev.model.Store;
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
public class StoreDaoImpl implements StoreDao{
    private final StoreRepository storeRepository;
    private final AddressRepository addressRepository;
    private final StaffRepository staffRepository;
    @Override
    public Collection<Store> readAll() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false)
                .map(entity -> new Store(
                        entity.getId(),
                        entity.getAddress().getAddress(),
                        entity.getStaff().getFirstName(),
                        entity.getStaff().getLastName(),
                        entity.getStaff().getAddress().getAddress(),
                        entity.getStaff().getEmail(),
                        entity.getStaff().getUserName(),
                        entity.getStaff().getPassWord(),
                        entity.getStaff().getActive(),
                        entity.getStaff().getAddress().getId(),
                        entity.getAddress().getId()

                ))
                .collect(Collectors.toList());
    }


    @Override
    public void createStore(Store store) throws UnknownStaffException, UnknownAddressException {
        StoreEntity storeEntity=createStore(store.getStoreAddressId());;

        StaffEntity staffEntity;
        staffEntity = StaffEntity.builder()
                .firstName(store.getStaffFirstName())
                .lastName(store.getStaffLastName())
                .address(queryAddress(store.getStaffAddressId()))
                .email(store.getStaffEmail())
                .active(store.getStaffActive())
                .userName(store.getStaffUsername())
                .passWord(store.getStaffPassword())
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        storeEntity.setStaff(staffEntity);

        staffEntity.setStore(storeEntity);
        try {
            storeRepository.save(storeEntity);
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

    }

    protected AddressEntity queryAddress(int addressId) throws  UnknownAddressException {
        Optional<AddressEntity> addressEntity = addressRepository.findById(addressId).stream()
                .findFirst();
        if (!addressEntity.isPresent()){
            throw new UnknownAddressException(addressEntity.get().getAddress());
        }
        return addressEntity.get();

    }
    protected StoreEntity createStore(int storeAddressId) throws UnknownAddressException {
        StoreEntity storeEntity=new StoreEntity();
        storeEntity.setAddress(queryAddress(storeAddressId));
        storeEntity.setLastUpdate(new Timestamp((new Date()).getTime()));


        return storeEntity;
    }

}
