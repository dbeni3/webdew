package hu.unideb.webdev.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@EqualsAndHashCode
public class Customer {

    private String store;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String active;
    private String creatDate;
}
