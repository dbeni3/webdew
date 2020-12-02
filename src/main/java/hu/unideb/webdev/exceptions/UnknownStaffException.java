package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.Staff;

public class UnknownStaffException extends Exception{
    private Staff staff;

    public UnknownStaffException(Staff staff) {
        this.staff = staff;
    }

    public UnknownStaffException(String message, Staff staff) {
        super(message);
        this.staff = staff;
    }

    public UnknownStaffException(String message) {
        super(message);
    }
}


