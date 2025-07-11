package com.picpay.customer.util;

import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerUpdateRequest;

import java.time.LocalDate;

public class CustomerUpdateRequestMock {

    public static CustomerUpdateRequest create() {
        return CustomerUpdateRequest.builder()
                .name("Maria")
                .email("maria@gmail.com")
                .cellphone("13999999999")
                .build();
    }

}
