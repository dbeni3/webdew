package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
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
 */
public interface StaffDao {

    void createStaff(Staff staff)  throws UnknownCountryException;
    Collection<Staff> readAll();

    void deleteStaff(Staff staff) throws UnknownAddressException;
}