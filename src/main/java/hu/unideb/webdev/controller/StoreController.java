package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.StoreDto;
import hu.unideb.webdev.controller.dto.StoreRequestDto;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
import hu.unideb.webdev.model.Store;
import hu.unideb.webdev.service.StoreService;
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
public class StoreController {

    private final StoreService service;

    @GetMapping("/store")
    public Collection<StoreDto> listStores(){
        return service.getAllStore()
                .stream()
                .map(model -> StoreDto.builder()
                        .address(model.getAddress())
                        .build())
                .collect(Collectors.toList());
    }


    @PostMapping("/store")
    public void record(@RequestBody StoreRequestDto requestDto)  {
        try{
        service.recordStore(new Store(
                requestDto.getStoreId(),
                requestDto.getAddress(),
                requestDto.getStaffFirstName(),
                requestDto.getStaffLastName(),
                requestDto.getStaffAddress(),
                requestDto.getStaffEmail(),
                requestDto.getStaffUsername(),
                requestDto.getStaffPassword(),
                requestDto.getStaffActive(),
                requestDto.getStaffAddressId(),
                requestDto.getStoreAddressId()
        ));
        } catch (UnknownStaffException | UnknownAddressException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }
}
