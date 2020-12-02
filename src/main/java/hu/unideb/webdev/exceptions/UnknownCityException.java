package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.City;

public class UnknownCityException extends Exception{
    private City city;

    public UnknownCityException(City city) {
        this.city = city;
    }

    public UnknownCityException(String message, City city) {
        super(message);
        this.city = city;
    }

    public UnknownCityException(String message) {
        super(message);
    }
}

