package com.picpay.customer.core.exception;

public class CreateAccountException extends RuntimeException {

    public CreateAccountException(Long id, Throwable throwable) {
        super(String.format("An error occurred while trying to create the customer's account with ID %d", id), throwable);
    }

}
