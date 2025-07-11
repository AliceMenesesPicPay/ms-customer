package com.picpay.customer.dataprovider.integration.finances.payload.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountRequest {

    private Long customerId;

    public static AccountRequest of(Long customerId) {
        return AccountRequest.builder()
                .customerId(customerId)
                .build();
    }

}
