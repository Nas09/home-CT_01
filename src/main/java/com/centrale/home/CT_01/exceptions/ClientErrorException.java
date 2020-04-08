package com.centrale.home.CT_01.exceptions;

public class ClientErrorException extends RuntimeException {

    private String message;

    public ClientErrorException(String message) {
        super(message);
    }

}
