package hu.unideb.webdev.dao;



import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Country;

import java.util.Collection;

/**
 * DAO = Data Access Object
 *
 * CRUD Methods:
 *  - Create
 *  - Read
 *  - Update
 *  - Delete
 *
 *
 */
public interface CountryDao {

    void createCountry(Country country) ;
    Collection<Country> readAll();
    void updateCountry(Country country) throws UnknownCountryException;
    void deleteCountry(Country country) ;
}