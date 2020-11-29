package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownAddressException;
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
 */
public interface CountryDao {

    void createCountry(Country country)  throws UnknownCountryException;
    Collection<Country> readAll();

    void deleteCountry(Country country) throws UnknownAddressException;
}