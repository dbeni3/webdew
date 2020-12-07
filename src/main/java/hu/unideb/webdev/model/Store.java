package hu.unideb.webdev.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Store {
    private String managerFirstName;
    private String managerLastName;
    private int addressId;
    private String address;
    private int managerAddressId;
    private String managerUserName;
    private String managerPassWord;
}
