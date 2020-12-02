package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.CityEntity;
import hu.unideb.webdev.dao.entity.CountryEntity;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Store;
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
public class StoreDaoImpl implements StoreDao{
    private final StoreRepository storeRepository;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    @Override
    public Collection<Store> readAll() {
        return StreamSupport.stream(storeRepository.findAll().spliterator(),false)
                .map(entity -> new Store(
                        entity.getAdress().getAddress(),
                        entity.getStaff().getFirstName()
                ))
                .collect(Collectors.toList());
    }

    protected CityEntity queryCity(String city, String country) throws UnknownCountryException {

        Optional<CityEntity> cityEntity = cityRepository.findByName(city).stream()
                .filter(entity -> entity.getCountry().getName().equals(country))
                .findFirst();
        if(!cityEntity.isPresent()){
            Optional<CountryEntity> countryEntity = Optional.ofNullable(countryRepository.findByName(country));
            if(!countryEntity.isPresent()){
                throw new UnknownCountryException(country);
            }
            cityEntity = Optional.ofNullable(CityEntity.builder()
                    .name(city)
                    .country(countryEntity.get())
                    .lastUpdate(new Timestamp((new Date()).getTime()))
                    .build());
            cityRepository.save(cityEntity.get());
            log.info("Recorded new City: {}, {}", city, country);
        }
        log.trace("City Entity: {}", cityEntity);
        return cityEntity.get();
    }
}
