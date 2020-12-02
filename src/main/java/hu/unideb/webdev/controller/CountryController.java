package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.AddressDto;
import hu.unideb.webdev.controller.dto.AddressRecordRequestDto;
import hu.unideb.webdev.controller.dto.CountryDto;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.Country;
import hu.unideb.webdev.service.CountryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
                        .name(model.getName())
                        .build())
                .collect(Collectors.toList());
    }


    @PostMapping("/country")
    public void record(@RequestBody CountryDto dto) {
        service.recordCountry(new Country(dto.getName()));
    }
}
