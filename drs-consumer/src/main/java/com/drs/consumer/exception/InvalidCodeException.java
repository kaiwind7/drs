package com.drs.consumer.exception;

public class InvalidCodeException extends RuntimeException{

    public InvalidCodeException(String message) {
        super(message);
    }
}
