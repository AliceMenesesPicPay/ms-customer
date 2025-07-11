package com.picpay.customer.util;

import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerRequest;

import java.time.LocalDate;

public class CustomerRequestMock {

    public static CustomerRequest create() {
        return CustomerRequest.builder()
                .name("Maria")
                .cpf("12345678901")
                .email("maria@gmail.com")
                .cellphone("13999999999")
                .birthday(LocalDate.of(2001, 1, 1))
                .build();
    }

}
