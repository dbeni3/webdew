package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestDto extends StoreDto {
   private int storeId;

   private String staffFirstName;
   private String staffLastName;
   private String staffAddress;
   private String staffEmail;
   private String staffUsername;
   private String staffPassword;
   private int staffActive;
   private int staffAddressId;
   private int storeAddressId;

}
