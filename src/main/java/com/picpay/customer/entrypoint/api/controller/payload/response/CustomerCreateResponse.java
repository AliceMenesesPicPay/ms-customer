package com.picpay.customer.entrypoint.api.controller.payload.response;

import com.picpay.customer.core.domain.Account;
import com.picpay.customer.core.domain.Customer;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class CustomerCreateResponse {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private String cellphone;
    private LocalDate birthday;
    private List<Account> accounts;

    public static CustomerCreateResponse from(Customer customer) {
        return CustomerCreateResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .email(customer.getEmail())
                .cellphone(customer.getCellphone())
                .birthday(customer.getBirthday())
                .accounts(customer.getAccounts())
                .build();
    }

}
