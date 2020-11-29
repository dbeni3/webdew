package hu.unideb.webdev.service;


import hu.unideb.webdev.dao.CountryDao;
import hu.unideb.webdev.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Slf4j
@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService{
    private final CountryDao countryDao;

    @Override
    public Collection<Country> getAllCountry() {
        return countryDao.readAll();
    }


    @Override
    public void recordCountry(Country country)  {
        countryDao.createCountry(country);
    }

    @Override
    public void deleteCountry(Country country)  {
        countryDao.deleteCountry(country);
    }

}
