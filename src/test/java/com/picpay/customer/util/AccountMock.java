package com.picpay.customer.util;

import com.picpay.customer.core.domain.Account;
import com.picpay.customer.core.domain.AccountType;

import static com.picpay.customer.util.HelpTest.ID;

public class AccountMock {

    public static Account create(AccountType accountType) {
        return Account.builder()
                .id(ID)
                .number("123456")
                .digit("3")
                .agency("0001")
                .accountType(accountType)
                .build();
    }

}
