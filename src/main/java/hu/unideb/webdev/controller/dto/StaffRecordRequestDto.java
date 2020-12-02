package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffRecordRequestDto extends StaffDto{
    private String city;
    private String country;
    private String storeCity;
    private String storeCountry;
}
