package com.microservice.employee.controller.error.type;


public class InvalidPasswordException extends RuntimeException {


    public InvalidPasswordException(String message) {
        super(message);
    }


}
