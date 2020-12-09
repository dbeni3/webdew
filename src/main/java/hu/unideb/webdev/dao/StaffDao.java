package hu.unideb.webdev.dao;


import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
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

    void createStaff(Staff staff) throws UnknownCountryException, UnknownStoreException, UnknownAddressException;
    Collection<Staff> readAll();
    void updateStaff(Staff staff) throws UnknownStaffException, UnknownAddressException, UnknownStoreException;
   // void deleteStaff(Staff staff);
}