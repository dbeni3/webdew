package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.CustomerDto;
import hu.unideb.webdev.controller.dto.CustomerRecordRequestDto;
import hu.unideb.webdev.controller.dto.StaffDto;
import hu.unideb.webdev.controller.dto.StaffRecordRequestDto;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.model.Staff;
import hu.unideb.webdev.service.StaffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
public class StaffController {

    private final StaffService service;

    @GetMapping("/staff")
    public List<?> listStaffs(){
        return service.getAllStaff()
                .stream()
                .map(model -> StaffDto.builder()
                        .firstName(model.getFirstName())
                        .lastName(model.getLastName())
                        .address(model.getAddress())
                        .email(model.getEmail())
                        .username(model.getUsername())
                        .password(model.getPassword())
                        .active(model.getActive())
                        .build())
                .collect(Collectors.toList());
    }
    @PostMapping("/staff")
    public void record(@RequestBody StaffRecordRequestDto requestDto) throws UnknownCountryException, UnknownStoreException {
        service.recordStaff(new Staff(
                requestDto.getStoreAddress(),
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getAddress(),
                requestDto.getEmail(),
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getActive(),
                requestDto.getCity(),
                requestDto.getCountry(),
                requestDto.getStoreCity(),
                requestDto.getStoreCountry()
        ));


    }
}
