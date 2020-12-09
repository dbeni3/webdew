package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.CustomerRecordRequestDto;
import hu.unideb.webdev.controller.dto.StaffDto;
import hu.unideb.webdev.controller.dto.StaffRecordRequestDto;
import hu.unideb.webdev.exceptions.*;
import hu.unideb.webdev.model.Customer;
import hu.unideb.webdev.model.Staff;
import hu.unideb.webdev.service.StaffService;
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
public class StaffController {

    private final StaffService service;



    @GetMapping("/staff")
    public Collection<StaffDto> listStaffs(){
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
    public void record(@RequestBody StaffRecordRequestDto requestDto)  {
        try{
        service.recordStaff(new Staff(
                requestDto.getStoreId(),
                requestDto.getStaffId(),
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getAddress(),
                requestDto.getEmail(),
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getActive(),
                requestDto.getStaffAddressId(),
                requestDto.getStoreAddressId()
        ));
        } catch (UnknownCountryException | UnknownStoreException | UnknownAddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
    /*@PutMapping("/staff")
    public void updateStaff(@RequestBody StaffRecordRequestDto requestDto)  {
        try {

        service.updateStaff(new Staff(
                requestDto.getStoreId(),
                requestDto.getStaffId(),
                requestDto.getFirstName(),
                requestDto.getLastName(),
                requestDto.getAddress(),
                requestDto.getEmail(),
                requestDto.getUsername(),
                requestDto.getPassword(),
                requestDto.getActive(),
                requestDto.getStaffAddressId(),
                requestDto.getStoreAddressId()
        ));
        } catch ( UnknownStoreException | UnknownAddressException | UnknownStaffException e) {
            e.printStackTrace();
        }
    }*/
}
