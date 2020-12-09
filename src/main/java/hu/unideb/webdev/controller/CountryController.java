package hu.unideb.webdev.controller;


import hu.unideb.webdev.controller.dto.CityDto;
import hu.unideb.webdev.controller.dto.CountryDto;
import hu.unideb.webdev.exceptions.UnknownCityException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.City;
import hu.unideb.webdev.model.Country;
import hu.unideb.webdev.service.CountryService;
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
public class CountryController {
    private final CountryService service;

    @GetMapping("/country")
    public Collection<CountryDto> listCountry(){
        return service.getAllCountry()
                .stream()
                .map(model -> CountryDto.builder()
                        .countryId(model.getCountryId())
                        .name(model.getName())
                        .build())
                .collect(Collectors.toList());
    }


    @PostMapping("/country")
    public void record(@RequestBody CountryDto dto) {
        service.recordCountry(new Country(
                dto.getCountryId(),
                dto.getName()));
    }

    @DeleteMapping("/country")
    public void deleteCountry(@RequestBody CountryDto countryDto){
        service.deleteCountry(new Country(countryDto.getCountryId(),
                countryDto.getName()));
    }
    @PutMapping("/country")
    public void updateCountry(@RequestBody CountryDto countryDto) {
        try {
            service.updateCountry(new Country(
                    countryDto.getCountryId(),
                    countryDto.getName()
            ));
        } catch (UnknownCountryException  e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }


}
