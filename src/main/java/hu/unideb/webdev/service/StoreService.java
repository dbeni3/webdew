package hu.unideb.webdev.service;


import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
import hu.unideb.webdev.model.Store;

import java.util.Collection;

public interface StoreService {
    Collection<Store> getAllStore();


    void recordStore(Store store) throws UnknownStaffException, UnknownAddressException;
    //void deleteStore(Store store);

}
