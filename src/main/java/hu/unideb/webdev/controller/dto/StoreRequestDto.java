package hu.unideb.webdev.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class StoreRequestDto {
   private String managerFirstName;
   private String managerLastName;
   private int addressId;
   private String address;
   private int managerAddressId;
   private String managerUserName;
   private String managerPassWord;
}
