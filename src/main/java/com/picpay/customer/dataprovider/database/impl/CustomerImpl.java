package com.picpay.customer.dataprovider.database.impl;

import com.picpay.customer.core.domain.Customer;
import com.picpay.customer.core.gateway.CustomerGateway;
import com.picpay.customer.dataprovider.database.entity.CustomerEntity;
import com.picpay.customer.dataprovider.database.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomerImpl implements CustomerGateway {

    private final CustomerRepository customerRepository;

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id).map(CustomerEntity::toCustomer);
    }

    @Override
    public Customer save(final Customer customer) {
        return customerRepository.save(CustomerEntity.from(customer)).toCustomer();
    }

}
