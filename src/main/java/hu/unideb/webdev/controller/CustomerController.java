package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.AddressRecordRequestDto;
import hu.unideb.webdev.controller.dto.CustomerDto;
import hu.unideb.webdev.controller.dto.CustomerRecordRequestDto;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownCustomerException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.service.CustomerService;
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
public class CustomerController {

    private final CustomerService service;


    @GetMapping("/customer")
    public Collection<CustomerDto> listCustomers() {
        return service.getAllCustomer()
                .stream()
                .map(model -> CustomerDto.builder()
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .email(model.getEmail())
                        .address(model.getAddress())
                        .active(model.getActive())
                        .build())
                .collect(Collectors.toList());
    }


    @PostMapping("/customer")
    public void record(@RequestBody CustomerRecordRequestDto requestDto) {
        try {
            service.recordCustomer(new Customer(
                    requestDto.getCustomerId(),
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getEmail(),
                    requestDto.getAddress(),
                    requestDto.getActive(),
                    requestDto.getStoreId(),
                    requestDto.getCity(),
                    requestDto.getCountry()
            ));
        } catch (UnknownCountryException | UnknownStoreException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @DeleteMapping("/customer")
    public void deleteAddress(@RequestBody CustomerRecordRequestDto requestDto) throws UnknownCustomerException {
        service.deleteCustomer(new Customer(
                requestDto.getCustomerId(),
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getEmail(),
                requestDto.getAddress(),
                requestDto.getActive(),
                requestDto.getStoreId(),
                requestDto.getCity(),
                requestDto.getCountry()
        ));
    }

    @PutMapping("/customer")
    public void updateCustomer(@RequestBody CustomerRecordRequestDto requestDto) {
        try {
            service.updateCustomer(new Customer(
                    requestDto.getCustomerId(),
                    requestDto.getFirstName(),
                    requestDto.getLastName(),
                    requestDto.getEmail(),
                    requestDto.getAddress(),
                    requestDto.getActive(),
                    requestDto.getStoreId(),
                    requestDto.getCity(),
                    requestDto.getCountry()
            ));
        } catch (UnknownCustomerException | UnknownStoreException | UnknownCountryException e) {
            e.printStackTrace();
        }
    }
}
