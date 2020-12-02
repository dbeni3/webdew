package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Customer;


import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 *  exeption
 */
public interface CustomerDao {

    void createCustomer(Customer customer) throws UnknownStoreException, UnknownCountryException;
    Collection<Customer> readAll();

    void deleteCustomer(Customer customer) ;
}