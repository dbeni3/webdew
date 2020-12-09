package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Staff {

    private int staffId;
    private int storeId;
    private String firstName;
    private String lastName;
    private String address;
    private String email;
    private String username;
    private String password;
    private int active;
    private int staffAddressId;
    private int storeAddressId;

}
