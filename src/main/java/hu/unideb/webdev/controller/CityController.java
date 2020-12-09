package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.AddressDto;
import hu.unideb.webdev.controller.dto.AddressRecordRequestDto;
import hu.unideb.webdev.controller.dto.CityDto;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCityException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.City;
import hu.unideb.webdev.service.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping("/city")
    public Collection<CityDto> listCities(){
        return service.getAllCity()
                .stream()
                .map(model -> CityDto.builder()
                        .name(model.getName())
                        .country(model.getCountry())
                        .build())
                .collect(Collectors.toList());
    }

    @PostMapping("/city")
    public void record(@RequestBody CityDto requestDto) {
        service.recordCity(new City(
                requestDto.getCityId(),
                requestDto.getName(),
                requestDto.getCountry()
        ));
    }

    @DeleteMapping("/city")
    public void deleteCity(@RequestBody CityDto cityDto) throws UnknownCityException {
        service.deleteCity(new City(
                cityDto.getCityId(),
                cityDto.getName(),
                cityDto.getCountry()
        ));
    }
    @PutMapping("/city")
    public void updateCity(@RequestBody CityDto cityDto) {
        try {
            service.updateCity(new City(
                    cityDto.getCityId(),
                    cityDto.getName(),
                    cityDto.getCountry()
            ));
        } catch (UnknownCountryException | UnknownCityException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
