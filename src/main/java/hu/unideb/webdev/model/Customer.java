package hu.unideb.webdev.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import static java.lang.Integer.parseInt;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Customer {
    private int customerId;
    private String firstName;
    private String LastName;
    private String email;
    private String address;
    private String active;
    private int storeId;
    private String city;
    private String country;






}
