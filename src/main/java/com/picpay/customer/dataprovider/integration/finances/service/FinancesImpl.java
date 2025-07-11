package com.picpay.customer.dataprovider.integration.finances.service;

import com.picpay.customer.core.domain.Account;
import com.picpay.customer.core.domain.AccountType;
import com.picpay.customer.core.exception.CreateAccountException;
import com.picpay.customer.core.gateway.FinancesGateway;
import com.picpay.customer.dataprovider.integration.finances.client.FinancesClient;
import com.picpay.customer.dataprovider.integration.finances.exception.FinancesIntegrationException;
import com.picpay.customer.dataprovider.integration.finances.payload.request.AccountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FinancesImpl implements FinancesGateway {

//    private final FinancesClient financesClient;

    @Override
    public List<Account> createAccount(Long customerId) {
        try {
//            return financesClient.createAccount(AccountRequest.of(customerId)).toAccount();
            var accountChecking = Account.builder()
                    .id(1L)
                    .digit("1")
                    .number("436862")
                    .agency("0001")
                    .accountType(AccountType.CHECKING)
                    .build();

            var accountSavings = Account.builder()
                    .id(1L)
                    .digit("1")
                    .number("123456")
                    .agency("0001")
                    .accountType(AccountType.SAVINGS)
                    .build();

            return List.of(accountChecking, accountSavings);
        } catch (RuntimeException e) { //TODO trocar para FeignEXception quando arrumar o problema do gradle
            throw new FinancesIntegrationException(customerId, e);
        }
    }



}
