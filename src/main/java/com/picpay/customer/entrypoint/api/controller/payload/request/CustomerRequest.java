package com.picpay.customer.entrypoint.api.controller.payload.request;

import com.picpay.customer.core.domain.Customer;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CustomerRequest {

    @NotBlank
    private String name;

    @NotBlank //TODO validar cpf
    private String cpf;

    @NotBlank
    @Email
    private String email;

    @NotBlank //TODO validar celular
    private String cellphone;

    @NotNull //TODO permitir só usuários maiores de idade
    private LocalDate birthday;

    public Customer toCustomer() {
        return Customer.builder()
                .name(name)
                .cpf(cpf)
                .email(email)
                .cellphone(cellphone)
                .birthday(birthday)
                .build();
    }

}
