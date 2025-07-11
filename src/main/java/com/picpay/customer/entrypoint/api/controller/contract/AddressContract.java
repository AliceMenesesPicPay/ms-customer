package com.picpay.customer.entrypoint.api.controller.contract;

import com.picpay.customer.entrypoint.api.controller.payload.request.AddressRequest;
import com.picpay.customer.entrypoint.api.controller.payload.response.AddressResponse;

public interface AddressContract {

    AddressResponse searchByCustomerId(Long customerId);
    AddressResponse create(Long customerId, AddressRequest addressRequest);
    AddressResponse update(Long customerId, AddressRequest addressRequest);

}
