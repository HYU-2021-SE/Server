package com.dionysos.winecellar.domain.auth.exception;

public class InvalidJwtTokenException extends IllegalArgumentException {
    public InvalidJwtTokenException() {
    }

    public InvalidJwtTokenException(String message) {
        super(message);
    }
}
