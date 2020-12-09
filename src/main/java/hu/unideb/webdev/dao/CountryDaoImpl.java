package hu.unideb.webdev.dao;

import hu.unideb.webdev.dao.entity.CityEntity;
import hu.unideb.webdev.dao.entity.CountryEntity;
import hu.unideb.webdev.exceptions.UnknownCityException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.City;
import hu.unideb.webdev.model.Country;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryDaoImpl implements CountryDao {
    private final CountryRepository countryRepository;

    @Override
    public void createCountry(Country country)  {
        CountryEntity countryEntity;

        countryEntity = CountryEntity.builder()
                .name(country.getName())
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("CountryEntity: {}", countryEntity);
        try {
            countryRepository.save(countryEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public Collection<Country> readAll() {
        return StreamSupport.stream(countryRepository.findAll().spliterator(),false)
                .map(entity -> new Country(
                        entity.getId(),
                        entity.getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCountry(Country country)  {
        Optional<CountryEntity> countryEntity = StreamSupport.stream(countryRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return country.getName().equals(entity.getName());
                }
        ).findAny();
        if(!countryEntity.isPresent()){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        countryRepository.delete(countryEntity.get());
    }
    @Override
    public void updateCountry(Country country) throws  UnknownCountryException {
        Optional<CountryEntity> countryEntity=countryRepository.findById(country.getCountryId());
        if (!countryEntity.isPresent()){
            throw new UnknownCountryException(String.format("Country Not Found %s",country), country);
        }

        if (!country.getName().equals("string")){
            countryEntity.get().setName(country.getName());
        }

        countryEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        countryRepository.save(countryEntity.get());
    }
}
