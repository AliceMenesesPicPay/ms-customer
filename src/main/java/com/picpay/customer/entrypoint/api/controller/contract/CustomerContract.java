package com.picpay.customer.entrypoint.api.controller.contract;

import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerRequest;
import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerUpdateRequest;
import com.picpay.customer.entrypoint.api.controller.payload.response.CustomerCreateResponse;
import com.picpay.customer.entrypoint.api.controller.payload.response.CustomerResponse;

public interface CustomerContract {

    CustomerResponse searchById(Long id);
    CustomerCreateResponse create(CustomerRequest customerRequest);
    CustomerResponse update(Long id, CustomerUpdateRequest customerUpdateRequest);

}
