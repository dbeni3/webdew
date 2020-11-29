package hu.unideb.webdev.service;


import hu.unideb.webdev.model.Country;

import java.util.Collection;

public interface CountryService {

    Collection<Country> getAllCountry();
    void recordCountry(Country country) ;
    void deleteCountry(Country country);
}
