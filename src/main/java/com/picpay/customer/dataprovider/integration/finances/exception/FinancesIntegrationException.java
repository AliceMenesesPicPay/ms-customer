package com.picpay.customer.dataprovider.integration.finances.exception;

public class FinancesIntegrationException extends RuntimeException {

    public FinancesIntegrationException(Long customerId, Throwable throwable) {
        super(String.format("An error occurred while trying to create the customer's account with ID %d", customerId), throwable);
    }

}