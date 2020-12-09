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
    private int soreId;
    private String address;
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
