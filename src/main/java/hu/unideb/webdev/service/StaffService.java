package hu.unideb.webdev.service;

import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Staff;

import java.util.Collection;

public interface StaffService {

    Collection<Staff> getAllStaff();
    void recordStaff(Staff staff) throws UnknownCountryException, UnknownStoreException, UnknownAddressException;
    //void deleteStaff(Staff staff);
    void updateStaff(Staff staff) throws UnknownAddressException, UnknownStaffException, UnknownStoreException;
}
