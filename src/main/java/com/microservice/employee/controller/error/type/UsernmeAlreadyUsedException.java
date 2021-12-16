package com.microservice.employee.controller.error.type;

public class UsernmeAlreadyUsedException extends RuntimeException {

    public UsernmeAlreadyUsedException(String message) {
        super(message);
    }
}
