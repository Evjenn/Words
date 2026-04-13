package org.example.exception;

public class CityLoadingException extends RuntimeException {

    public CityLoadingException(String message) {
        super(message);
    }

    public CityLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
