package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.*;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Staff;
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
                        entity.getStaff().getFirstName(),
                        entity.getStaff().getLastName(),
                        entity.getAddress().getId(),
                        entity.getAddress().getAddress(),
                        entity.getStaff().getAddress().getId(),
                        entity.getStaff().getUserName(),
                        entity.getStaff().getPassWord()

                ))
                .collect(Collectors.toList());
    }


    @Override
    public void createStore(Store store) throws UnknownStaffException, UnknownAddressException {
        StoreEntity storeEntity;

        storeEntity = StoreEntity.builder()
                .staff(queryStaff(store.getManagerFirstName(),store.getManagerLastName(),
                        store.getAddressId(),store.getManagerUserName(),store.getManagerPassWord()))
                .address(queryAddress(store.getAddressId()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("StaffEntity: {}", storeEntity);
        try {
            storeRepository.save(storeEntity);
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

    protected StaffEntity queryStaff(String firstName,String lastName,
                                     int addressId,String username,
                                     String passWord) throws UnknownStaffException, UnknownAddressException {

        StaffEntity staffEntity = StaffEntity.builder()
                .firstName(firstName)
                .lastName(lastName)
                .address(queryAddress(addressId))
                .email("")
                .store(new StoreEntity())
                .active("1")
                .userName(username)
                .passWord(passWord)
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        staffEntity.getStore().setId(11);
        staffRepository.save(staffEntity);
        return staffEntity;

    }
}
