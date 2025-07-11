package com.picpay.customer.dataprovider.integration.finances.payload.response;

import com.picpay.customer.core.domain.Account;
import com.picpay.customer.core.domain.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long id;
    private String number;
    private String digit;
    private String agency;
    private AccountType accountType;

    public Account toAccount() {
        return Account.builder()
                .id(id)
                .number(number)
                .digit(digit)
                .agency(agency)
                .accountType(accountType)
                .build();
    }

}
