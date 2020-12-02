package hu.unideb.webdev.dao;


import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Staff;

import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 *  Exeption!!
 */
public interface StaffDao {

    void createStaff(Staff staff) throws UnknownCountryException, UnknownStoreException;
    Collection<Staff> readAll();

   // void deleteStaff(Staff staff);
}