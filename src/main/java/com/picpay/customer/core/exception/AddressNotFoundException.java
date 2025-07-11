package com.picpay.customer.core.exception;

public class AddressNotFoundException extends EntityNotFoundException {

    public AddressNotFoundException(Long customerId) {
        super(String.format("This customer with ID %d does not have an address registered", customerId));
    }

}
