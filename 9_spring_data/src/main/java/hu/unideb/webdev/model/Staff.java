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

    private String store;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String active;
}
