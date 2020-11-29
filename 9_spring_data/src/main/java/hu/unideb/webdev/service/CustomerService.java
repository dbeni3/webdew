package hu.unideb.webdev.service;


import hu.unideb.webdev.model.Customer;

import java.util.Collection;

public interface CustomerService {

    Collection<Customer> getAllCustomer();
    void recordCustomer(Customer customer) ;
    void deleteCustomer(Customer customer);
}
