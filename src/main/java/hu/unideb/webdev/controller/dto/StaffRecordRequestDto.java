package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StaffRecordRequestDto extends StaffDto  {
    private int staffAddressId;
    private int storeAddressId;
    private int staffId;
    private int storeId;
}
