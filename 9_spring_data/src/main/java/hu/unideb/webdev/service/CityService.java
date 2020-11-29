package hu.unideb.webdev.service;


import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.City;

import java.util.Collection;

public interface CityService {

    Collection<City> getAllCity();
    void recordCity(City city) ;
    void deleteCity(City city);
}
