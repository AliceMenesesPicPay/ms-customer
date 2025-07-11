package com.picpay.customer.entrypoint.api.controller.payload.request;

import com.picpay.customer.core.domain.Address;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AddressRequest {

    @NotBlank
    private String street;

    @NotBlank
    private String number;

    @NotBlank
    private String complement;

    @NotBlank
    private String neighborhood;

    @NotBlank
    private String city;

    @NotBlank //Validar estado valido
    private String state;

    @NotBlank
    private String cep;

    public Address toAddress() {
        return Address.builder()
                .street(street)
                .number(number)
                .complement(complement)
                .neighborhood(neighborhood)
                .city(city)
                .state(state)
                .cep(cep)
                .build();
    }

}
