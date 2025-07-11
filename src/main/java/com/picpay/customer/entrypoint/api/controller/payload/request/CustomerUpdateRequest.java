package com.picpay.customer.entrypoint.api.controller.payload.request;

import com.picpay.customer.core.domain.Customer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class CustomerUpdateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String cellphone;

    public Customer toCustomerWithId(Long id) {
        return Customer.builder()
                .id(id)
                .name(name)
                .email(email)
                .cellphone(cellphone)
                .build();
    }

}
