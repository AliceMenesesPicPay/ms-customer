package com.picpay.customer.entrypoint.api.controller.handler.payload;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ErrorDetailResponse {

    private String name;
    private String reason;

}
