package com.picpay.customer.core.exception;

public class AddressAlreadyExistsException extends RuntimeException {

    public AddressAlreadyExistsException(Long customerId) {
        super(String.format("Address already exists for customer with ID %d", customerId));
    }

}
