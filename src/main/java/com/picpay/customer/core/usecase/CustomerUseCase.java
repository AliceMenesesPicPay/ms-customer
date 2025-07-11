package com.picpay.customer.core.usecase;

import com.picpay.customer.core.domain.Customer;
import com.picpay.customer.core.exception.CreateAccountException;
import com.picpay.customer.core.exception.CustomerNotFoundException;
import com.picpay.customer.core.gateway.CustomerGateway;
import com.picpay.customer.core.gateway.FinancesGateway;
import com.picpay.customer.dataprovider.integration.finances.exception.FinancesIntegrationException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.DatagramPacket;

@Service
@RequiredArgsConstructor
public class CustomerUseCase {

    private final CustomerGateway customerGateway;
    private final FinancesGateway financesGateway;

    public Customer searchById(final Long id) {
        return customerGateway.findById(id).orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Transactional
    public Customer create(final Customer customer) {
        try {
            var customerSaved = customerGateway.save(customer);
            var account = financesGateway.createAccount(customerSaved.getId());
            customerSaved.setAccounts(account);
            return customerSaved;
        } catch (FinancesIntegrationException e) {
            throw new CreateAccountException(customer.getId(), e);
        }
    }

    public Customer update(final Customer customer) {
        var existingCustomer = customerGateway.findById(customer.getId())
                .orElseThrow(() -> new CustomerNotFoundException(customer.getId()));
        existingCustomer.update(customer);
        return customerGateway.save(existingCustomer);
    }

    public Customer save(Customer customer) {
        return customerGateway.save(customer);
    }

}
