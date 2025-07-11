package com.picpay.customer.entrypoint.api.controller.payload.response;

import com.picpay.customer.core.domain.Customer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CustomerResponse {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String cellphone;
    private LocalDate birthday;

    public static CustomerResponse from(Customer customer) {
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .email(customer.getEmail())
                .cellphone(customer.getCellphone())
                .birthday(customer.getBirthday())
                .build();
    }

}
