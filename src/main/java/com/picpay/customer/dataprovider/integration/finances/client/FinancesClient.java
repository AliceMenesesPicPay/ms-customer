package com.picpay.customer.dataprovider.integration.finances.client;

import com.picpay.customer.dataprovider.integration.finances.payload.request.AccountRequest;
import com.picpay.customer.dataprovider.integration.finances.payload.response.AccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(name = "finances-client", url = "${picpay.services.finances.url}")
//@Headers({"Content-Type: application/json; charset=utf-8", "Accept: application/json; charset=utf-8", })
public interface FinancesClient {

    @PostMapping("/accounts")
    AccountResponse createAccount(@RequestBody AccountRequest accountRequest);

}
