package com.picpay.customer.util;

import com.picpay.customer.core.domain.Address;

import java.time.LocalDateTime;

import static com.picpay.customer.util.HelpTest.ID;

public class AddressMock {

    public static Address create() {
        return Address.builder()
                .id(ID)
                .street("Rua das Flores")
                .number("123")
                .complement("Apto 101")
                .neighborhood("Jardim das Rosas")
                .city("São Paulo")
                .state("SP")
                .cep("01234-567")
                .createdAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .updatedAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .build();
    }

    public static Address createWithOtherData() {
        return Address.builder()
                .id(ID)
                .street("Avenida das Palmeiras")
                .number("321")
                .complement("Apto 42")
                .neighborhood("Jardim das Palmas")
                .city("São Vicente")
                .state("SP")
                .cep("03134-567")
                .createdAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .updatedAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .build();
    }

}
