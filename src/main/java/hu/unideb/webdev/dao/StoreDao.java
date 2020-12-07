package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
import hu.unideb.webdev.model.Store;


import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 *
 *  Exeption
 */
public interface StoreDao {

    void createStore(Store store) throws UnknownStaffException, UnknownAddressException;
    Collection<Store> readAll();

    //void deleteStore(Store store) ;
}