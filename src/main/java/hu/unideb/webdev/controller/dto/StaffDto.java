package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffDto {
    private String storeAddress;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String username;
    private String password;
    private String active;
}

