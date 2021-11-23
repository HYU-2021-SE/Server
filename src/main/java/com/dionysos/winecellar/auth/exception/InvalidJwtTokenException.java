package com.dionysos.winecellar.auth.exception;

public class InvalidJwtTokenException extends IllegalArgumentException {
    public InvalidJwtTokenException() {
    }

    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
