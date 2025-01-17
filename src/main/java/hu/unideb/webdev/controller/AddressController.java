package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.AddressDto;
import hu.unideb.webdev.controller.dto.AddressRecordRequestAddressDto;
import hu.unideb.webdev.controller.dto.AddressRecordRequestDto;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.service.AddressService;
import io.swagger.annotations.ApiOperation;
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
public class AddressController {

    private final AddressService service;

    @GetMapping("/address")
    public Collection<AddressDto> listAddresses() {
        return service.getAllAddress()
                .stream()
                .map(model -> AddressDto.builder()
                        .address(model.getAddress())
                        .address2(model.getAddress2())
                        .district(model.getDistrict())
                        .city(model.getCity())
                        .country(model.getCountry())
                        .build())
                .collect(Collectors.toList());
    }

    /*@GetMapping("/addressByAddress")
    public Collection<AddressDto> listAddressesByAddress(@RequestBody AddressRecordRequestAddressDto requestDto){
        return service.getAddressByAddress(requestDto.getAddress())
                .stream()
                .map(model -> AddressDto.builder()
                        .address(model.getAddress())
                        .address2(model.getAddress2())
                        .district(model.getDistrict())
                        .city(model.getCity())
                        .country(model.getCountry())
                        .build())
                .collect(Collectors.toList());
    }*/


    @PostMapping("/address")
    public void record(@RequestBody AddressRecordRequestDto requestDto) {
        try {
            service.recordAddress(new Address(
                    requestDto.getAddress(),
                    requestDto.getAddress2(),
                    requestDto.getDistrict(),
                    requestDto.getCity(),
                    requestDto.getCountry(),
                    requestDto.getPostalCode(),
                    requestDto.getPhone(),
                    requestDto.getAddressId()
            ));
        } catch (UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/address")
    public void deleteAddress(@RequestBody AddressRecordRequestDto requestDto) {
        try {
            service.deleteAddress(new Address(
                    requestDto.getAddress(),
                    requestDto.getAddress2(),
                    requestDto.getDistrict(),
                    requestDto.getCity(),
                    requestDto.getCountry(),
                    requestDto.getPostalCode(),
                    requestDto.getPhone(),
                    requestDto.getAddressId()
            ));
        } catch (UnknownAddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @PutMapping("/address")
    public void updateAddress(@RequestBody AddressRecordRequestDto requestDto) {
        try {
            service.updateAddress(new Address(
                    requestDto.getAddress(),
                    requestDto.getAddress2(),
                    requestDto.getDistrict(),
                    requestDto.getCity(),
                    requestDto.getCountry(),
                    requestDto.getPostalCode(),
                    requestDto.getPhone(),
                    requestDto.getAddressId()
            ));
        } catch (UnknownAddressException | UnknownCountryException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
