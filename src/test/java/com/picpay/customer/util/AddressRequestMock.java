package com.picpay.customer.util;

import com.picpay.customer.entrypoint.api.controller.payload.request.AddressRequest;

public class AddressRequestMock {

    public static AddressRequest create() {
        return AddressRequest.builder()
                .street("Rua das Flores")
                .number("123")
                .complement("Apto 101")
                .neighborhood("Jardim das Rosas")
                .city("São Paulo")
                .state("SP")
                .cep("01234-567")
                .build();
    }

}
