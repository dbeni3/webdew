package hu.unideb.webdev.service;


import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Customer;

import java.util.Collection;

public interface CustomerService {

    Collection<Customer> getAllCustomer();
    void recordCustomer(Customer customer) throws UnknownCountryException, UnknownStoreException;
    void deleteCustomer(Customer customer) throws UnknownCustomerException;
    void updateCustomer(Customer customer) throws UnknownCustomerException, UnknownCountryException, UnknownStoreException;

}
