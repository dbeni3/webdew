package hu.unideb.webdev.controller;

import hu.unideb.webdev.controller.dto.StaffDto;
import hu.unideb.webdev.controller.dto.StoreDto;
import hu.unideb.webdev.service.StaffService;
import hu.unideb.webdev.service.StoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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
                        .manager(model.getManager())
                        .address(model.getAddress())
                        .build())
                .collect(Collectors.toList());
    }
}
