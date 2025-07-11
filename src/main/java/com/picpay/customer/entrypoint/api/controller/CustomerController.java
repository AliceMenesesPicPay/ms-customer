package com.picpay.customer.entrypoint.api.controller;

import com.picpay.customer.core.usecase.CustomerUseCase;
import com.picpay.customer.entrypoint.api.controller.contract.CustomerContract;
import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerRequest;
import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerUpdateRequest;
import com.picpay.customer.entrypoint.api.controller.payload.response.CustomerCreateResponse;
import com.picpay.customer.entrypoint.api.controller.payload.response.CustomerResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController implements CustomerContract {

    private final CustomerUseCase customerUseCase;

    @GetMapping("/{id}")
    @Override
    public CustomerResponse searchById(@PathVariable Long id) {
        var customer = customerUseCase.searchById(id);
        return CustomerResponse.from(customer);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Override
    public CustomerCreateResponse create(@Valid @RequestBody CustomerRequest customerRequest) {
        var customer = customerUseCase.create(customerRequest.toCustomer());
        return CustomerCreateResponse.from(customer);
    }

    @PutMapping("/{id}")
    @Override
    public CustomerResponse update(@PathVariable Long id, @Valid @RequestBody CustomerUpdateRequest customerUpdateRequest) {
        var customer = customerUseCase.update(customerUpdateRequest.toCustomerWithId(id));
        return CustomerResponse.from(customer);
    }

}
