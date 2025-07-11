package com.picpay.customer.util;

import com.picpay.customer.core.domain.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static com.picpay.customer.util.HelpTest.ID;

public class CustomerMock {

    public static Customer create() {
        return Customer.builder()
                .id(ID)
                .name("Maria")
                .cpf("12345678901")
                .email("maria@gmail.com")
                .cellphone("13999999999")
                .birthday(LocalDate.of(2001, 1, 1))
                .createdAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .updatedAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .build();
    }

    public static Customer createWithOtherData() {
        return Customer.builder()
                .id(ID)
                .name("Alice")
                .cpf("12345678901")
                .email("alice@gmail.com")
                .cellphone("11999999999")
                .birthday(LocalDate.of(2001, 1, 1))
                .createdAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .updatedAt(LocalDateTime.of(2025, 1, 1, 10, 0))
                .build();
    }

}
