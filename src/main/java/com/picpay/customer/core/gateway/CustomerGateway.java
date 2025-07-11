package com.picpay.customer.core.gateway;

import com.picpay.customer.core.domain.Customer;

import java.util.Optional;

public interface CustomerGateway {

    Optional<Customer> findById(Long id);
    Customer save(Customer customer);

}
