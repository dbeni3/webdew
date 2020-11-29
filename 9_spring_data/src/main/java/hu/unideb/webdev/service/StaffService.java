package hu.unideb.webdev.service;

import hu.unideb.webdev.model.Staff;

import java.util.Collection;

public interface StaffService {

    Collection<Staff> getAllStaff();
    void recordStaff(Staff staff) ;
    void deleteStaff(Staff staff);
}
