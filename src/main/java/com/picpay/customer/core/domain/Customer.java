package com.picpay.customer.core.domain;

import com.picpay.customer.core.exception.AddressAlreadyExistsException;
import com.picpay.customer.core.exception.AddressNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Customer {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String cellphone;
    private LocalDate birthday;
    private List<Account> accounts;
    private Address address;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void update(final Customer customer) {
        name = customer.getName();
        email = customer.getEmail();
        cellphone = customer.getCellphone();
    }

    public void updateAddress(Address address) {
        if (this.address == null) {
            throw new AddressNotFoundException(id);
        }

        this.address.update(address);
    }

    public void addAddress(Address address) {
        if (this.address != null) {
            throw new AddressAlreadyExistsException(id);
        }

        this.address = address;
    }

}
