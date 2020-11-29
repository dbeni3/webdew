package hu.unideb.webdev.service;

import hu.unideb.webdev.dao.CityDao;
import hu.unideb.webdev.model.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;


@Slf4j
@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {
    private final CityDao cityDao;

    @Override
    public Collection<City> getAllCity() {
        return cityDao.readAll();
    }



    @Override
    public void recordCity(City city)  {
        cityDao.createCity(city);
    }

    @Override
    public void deleteCity(City city)  {
        cityDao.deleteCity(city);
    }
}