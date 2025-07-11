package com.picpay.customer.entrypoint.api.controller.payload.response;

import com.picpay.customer.core.domain.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressResponse {

    private Long id;
    private String street;
    private String number;
    private String complement;
    private String neighborhood;
    private String city;
    private String state;
    private String cep;

    public static AddressResponse from(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .cep(address.getCep())
                .build();
    }

}
