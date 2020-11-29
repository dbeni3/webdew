package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.CustomerDao;
import hu.unideb.webdev.model.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerDao customerDao;

    @Override
    public Collection<Customer> getAllCustomer() {
        return customerDao.readAll();
    }


    @Override
    public void recordCustomer(Customer customer)  {
        customerDao.createCustomer(customer);
    }

    @Override
    public void deleteCustomer(Customer customer)  {
        customerDao.deleteCustomer(customer);
    }
}
