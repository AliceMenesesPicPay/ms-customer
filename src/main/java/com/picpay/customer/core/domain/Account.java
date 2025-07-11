package com.picpay.customer.core.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Account {

    private Long id;
    private String number;
    private String digit;
    private String agency;
    private AccountType accountType;

}
