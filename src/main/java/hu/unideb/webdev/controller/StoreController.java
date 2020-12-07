package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.StaffDto;
import hu.unideb.webdev.controller.dto.StaffRecordRequestDto;
import hu.unideb.webdev.controller.dto.StoreDto;
import hu.unideb.webdev.controller.dto.StoreRequestDto;
import hu.unideb.webdev.exceptions.UnknownAddressException;
import hu.unideb.webdev.exceptions.UnknownCountryException;
import hu.unideb.webdev.exceptions.UnknownStaffException;
import hu.unideb.webdev.exceptions.UnknownStoreException;
import hu.unideb.webdev.model.Staff;
import hu.unideb.webdev.model.Store;
import hu.unideb.webdev.service.StaffService;
import hu.unideb.webdev.service.StoreService;
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
public class StoreController {

    private final StoreService service;

    @GetMapping("/store")
    public List<?> listStores(){
        return service.getAllStore()
                .stream()
                .map(model -> StoreDto.builder()
                        .manager(model.getManagerUserName())
                        .address(model.getAddress())
                        .build())
                .collect(Collectors.toList());
    }
    @PostMapping("/store")
    public void record(@RequestBody StoreRequestDto requestDto) throws  UnknownStaffException, UnknownAddressException {
        service.recordStore(new Store(
                requestDto.getManagerFirstName(),
                requestDto.getManagerLastName(),
                requestDto.getAddressId(),
                requestDto.getAddress(),
                requestDto.getManagerAddressId(),
                requestDto.getManagerUserName(),
                requestDto.getManagerPassWord()
        ));
    }
}
