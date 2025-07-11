package com.picpay.customer.core.exception;

public class CustomerNotFoundException extends EntityNotFoundException {

    public CustomerNotFoundException(Long id) {
        super(String.format("There isn't an customer with ID %d", id));
    }

}
