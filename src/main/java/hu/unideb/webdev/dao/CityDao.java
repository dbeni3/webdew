package hu.unideb.webdev.dao;

import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCityException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.City;

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
public interface CityDao {

    void createCity(City city)  ;
    Collection<City> readAll();
    void updateCity(City city) throws UnknownCityException, UnknownCountryException;
    void deleteCity(City city) throws UnknownCityException;
}