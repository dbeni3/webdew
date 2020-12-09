package hu.unideb.webdev.exceptions;


import hu.unideb.webdev.model.Country;
import lombok.Data;

@Data
public class UnknownCountryException extends Exception {
    private Country country;
    public UnknownCountryException() {
    }
    public UnknownCountryException(String message, Country country) {
        super(message);
        this.country = country;
    }
    public UnknownCountryException(String message) {
        super(message);
    }
}
