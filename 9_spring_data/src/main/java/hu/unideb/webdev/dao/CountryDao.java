package hu.unideb.webdev.dao;



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
 *  exeption kezelés
 */
public interface CountryDao {

    void createCountry(Country country) ;
    Collection<Country> readAll();

    void deleteCountry(Country country) ;
}