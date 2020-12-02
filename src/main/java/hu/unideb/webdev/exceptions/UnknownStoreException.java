package hu.unideb.webdev.exceptions;

import hu.unideb.webdev.model.Address;
import hu.unideb.webdev.model.Store;

public class UnknownStoreException extends Exception{
    private Store store;

    public UnknownStoreException(Store store) {
        this.store = store;
    }

    public UnknownStoreException(String message, Store store) {
        super(message);
        this.store = store;
    }

    public UnknownStoreException(String message) {
        super(message);
    }
}
