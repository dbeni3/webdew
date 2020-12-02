package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.AddressRecordRequestDto;
import hu.unideb.webdev.controller.dto.CustomerDto;
import hu.unideb.webdev.controller.dto.CustomerRecordRequestDto;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService service;

    @GetMapping("/customer")
    public List<?> listCustomers(){
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
    public void record(@RequestBody CustomerRecordRequestDto requestDto) throws UnknownCountryException, UnknownStoreException {
       service.recordCustomer(new Customer(
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
}
