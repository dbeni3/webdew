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

    private String manager;
    private String address;

}
