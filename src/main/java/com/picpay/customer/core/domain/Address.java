package com.picpay.customer.core.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Address {

    private Long id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void update(Address address) {
        street = address.getStreet();
        number = address.getNumber();
        complement = address.getComplement();
        neighborhood = address.getNeighborhood();
        city = address.getCity();
        state = address.getState();
        cep = address.getCep();
    }

}
