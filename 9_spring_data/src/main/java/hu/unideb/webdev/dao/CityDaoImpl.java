package hu.unideb.webdev.dao;


import hu.unideb.webdev.dao.entity.CityEntity;
import hu.unideb.webdev.exceptions.UnknownAddressException;
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

/**
 * Még exeptionkezelés és teszt kell hozzá
 *
 *
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class CityDaoImpl implements CityDao{
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    public void createCity(City city)  {
        CityEntity cityEntity;

        cityEntity = CityEntity.builder()
                .name(city.getName())
                .country(countryRepository.findByName(city.getCountry()))
                .lastUpdate(new Timestamp((new Date()).getTime()))
                .build();
        log.info("CityEntity: {}", cityEntity);
        try {
            cityRepository.save(cityEntity);
        }
        catch(Exception e){
            log.error(e.getMessage());
        }
    }

   /* protected CountryEntity queryCountry(String country) throws UnknownCountryException {

        Optional<CityEntity> cityEntity = cityRepository.findByName(city).stream()
                .filter(entity -> entity.getCountry().getName().equals(country))
                .findFirst();
        if(!cityEntity.isPresent()){
            Optional<CountryEntity> countryEntity = countryRepository.findByName(country);
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
    }*/

    @Override
    public Collection<City> readAll() {
        return StreamSupport.stream(cityRepository.findAll().spliterator(),false)
                .map(entity -> new City(
                        entity.getName(),
                        entity.getCountry().getName()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCity(City city)  {
        Optional<CityEntity> cityEntity = StreamSupport.stream(cityRepository.findAll().spliterator(),false).filter(
                entity ->{
                    return city.getName().equals(entity.getName())  &&
                            city.getCountry().equals(entity.getCountry());
                }
        ).findAny();
        if(!cityEntity.isPresent()){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        cityRepository.delete(cityEntity.get());
    }


}
