package hu.unideb.webdev.service;



import hu.unideb.webdev.exceptions.UnknownCityException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.City;

import java.util.Collection;

public interface CityService {

    Collection<City> getAllCity();
    void recordCity(City city) ;
    void deleteCity(City city) throws UnknownCityException;
    void updateCity(City city) throws UnknownCityException, UnknownCountryException;
}
