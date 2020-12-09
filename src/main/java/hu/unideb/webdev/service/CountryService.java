package hu.unideb.webdev.service;


import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Country;

import java.util.Collection;

public interface CountryService {

    Collection<Country> getAllCountry();
    void recordCountry(Country country) ;
    void deleteCountry(Country country);
    void updateCountry(Country country) throws UnknownCountryException;

}
