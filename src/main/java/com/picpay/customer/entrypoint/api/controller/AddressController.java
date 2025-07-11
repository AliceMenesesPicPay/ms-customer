package com.picpay.customer.entrypoint.api.controller;

import com.picpay.customer.core.usecase.AddressUseCase;
import com.picpay.customer.entrypoint.api.controller.contract.AddressContract;
import com.picpay.customer.entrypoint.api.controller.payload.request.AddressRequest;
import com.picpay.customer.entrypoint.api.controller.payload.response.AddressResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/customers/{customerId}/addresses")
@RequiredArgsConstructor
public class AddressController implements AddressContract {

    private final AddressUseCase addressUseCase;

    @GetMapping
    @Override
    public AddressResponse searchByCustomerId(@PathVariable Long customerId) {
        var address = addressUseCase.searchByCustomerId(customerId);
        return AddressResponse.from(address);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    @Override
    public AddressResponse create(@PathVariable Long customerId, @Valid @RequestBody AddressRequest addressRequest) {
        var address = addressUseCase.create(customerId, addressRequest.toAddress());
        return AddressResponse.from(address);
    }

    @PutMapping
    @Override
    public AddressResponse update(@PathVariable Long customerId, @Valid @RequestBody AddressRequest addressRequest) {
        var address = addressUseCase.update(customerId, addressRequest.toAddress());
        return AddressResponse.from(address);
    }

}
