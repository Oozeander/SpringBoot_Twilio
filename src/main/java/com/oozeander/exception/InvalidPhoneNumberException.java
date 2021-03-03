package com.oozeander.exception;

public class InvalidPhoneNumberException extends RuntimeException {
    public InvalidPhoneNumberException(String phoneNumber) {
        super("Phone number : " + phoneNumber + " is invalid");
    }
}