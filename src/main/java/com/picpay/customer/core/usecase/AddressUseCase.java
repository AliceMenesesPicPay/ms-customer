package com.picpay.customer.core.usecase;

import com.picpay.customer.core.domain.Address;
import com.picpay.customer.core.exception.AddressNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressUseCase {

    private final CustomerUseCase customerUseCase;

    public Address searchByCustomerId(final Long customerId) {
        var customer = customerUseCase.searchById(customerId);
        return Optional.ofNullable(customer.getAddress()).orElseThrow(() -> new AddressNotFoundException(customerId));
    }

    public Address create(final Long customerId, final Address address) {
        var customer = customerUseCase.searchById(customerId);
        customer.addAddress(address);
        return customerUseCase.save(customer).getAddress();
    }

    public Address update(final Long customerId, final Address address) {
        var customer = customerUseCase.searchById(customerId);
        customer.updateAddress(address);
        return customerUseCase.save(customer).getAddress();
    }

}
