package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.AddressEntity;
import hu.unideb.webdev.dao.entity.CityEntity;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCityException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.City;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.GeometryFactory;
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
public class CityDaoImpl implements CityDao {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    public void createCity(City city) {
        CityEntity cityEntity;

        cityEntity = CityEntity.builder()
                .name(city.getName())
                .country(countryRepository.findByName(city.getCountry()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("CityEntity: {}", cityEntity);
        try {
            cityRepository.save(cityEntity);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }



    @Override
    public Collection<City> readAll() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
                .map(entity -> new City(
                        entity.getId(),
                        entity.getName(),
                        entity.getCountry().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCity(City city) throws UnknownCityException {

        Optional<CityEntity> cityEntity = StreamSupport.stream(cityRepository.findAll().spliterator(), false).filter(
                entity -> {
                    return city.getName().equals(entity.getName()) &&
                            city.getCountry().equals(entity.getCountry().getName());
                }
        ).findAny();

        System.out.println(cityEntity.get().getName());
        if (!cityEntity.isPresent()) {
            throw new UnknownCityException(String.format("City Not Found %s", city), city);
        }

        cityRepository.delete(cityEntity.get());
    }
    @Override
    public void updateCity(City city) throws UnknownCountryException, UnknownCityException {
        Optional<CityEntity> cityEntity=cityRepository.findById(city.getCityId());
        if (!cityEntity.isPresent()){
            throw new UnknownCityException(String.format("City Not Found %s",city), city);
        }

        if (!city.getName().equals("string")){
            cityEntity.get().setName(city.getName());
        }
        if (!city.getCountry().equals("string")){
            cityEntity.get().setCountry(countryRepository.findByName(city.getCountry()));
        }

        cityEntity.get().setLastUpdate(new Timestamp((new Date()).getTime()));

        cityRepository.save(cityEntity.get());
    }

}