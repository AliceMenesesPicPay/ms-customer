package com.picpay.customer.core.gateway;

import com.picpay.customer.core.domain.Account;

import java.util.List;

public interface FinancesGateway {

    List<Account> createAccount(Long customerId);

}
