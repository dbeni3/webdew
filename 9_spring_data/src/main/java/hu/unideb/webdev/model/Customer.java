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
    private String store;
    private String firstName;
    private String LastName;
    private String email;
    private String adress;
    private String active;
    private String createDate;



}
