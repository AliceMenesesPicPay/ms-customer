package com.picpay.customer.entrypoint.api.controller;

import com.picpay.customer.core.domain.Customer;
import com.picpay.customer.core.usecase.CustomerUseCase;
import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerRequest;
import com.picpay.customer.entrypoint.api.controller.payload.request.CustomerUpdateRequest;
import com.picpay.customer.entrypoint.api.controller.payload.response.CustomerCreateResponse;
import com.picpay.customer.entrypoint.api.controller.payload.response.CustomerResponse;
import com.picpay.customer.util.CustomerMock;
import com.picpay.customer.util.CustomerRequestMock;
import com.picpay.customer.util.CustomerUpdateRequestMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.picpay.customer.util.HelpTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @InjectMocks
    private CustomerController customerController;

    @Captor
    private ArgumentCaptor<Customer> customerCaptor;

    @Test
    void whenSearchByCustomerIdThenReturnCustomer() {
        var customer = CustomerMock.create();

        when(customerUseCase.searchById(ID)).thenReturn(customer);

        var response = customerController.searchById(ID);

        verify(customerUseCase).searchById(ID);

        assertThat(response)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(CustomerResponse.from(customer));
    }

    @Test
    void whenCreateCustomerThenReturnCustomer() {
        var customer = CustomerMock.create();
        var customerRequest = CustomerRequestMock.create();

        when(customerUseCase.create(any(Customer.class))).thenReturn(customer);

        var response = customerController.create(customerRequest);

        verify(customerUseCase).create(customerCaptor.capture());

        assertThat(customerCaptor.getValue())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(customerRequest.toCustomer());

        assertThat(response)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(CustomerCreateResponse.from(customer));
    }

    @Test
    void whenUpdateCustomerThenReturnCustomer() {
        var customer = CustomerMock.create();
        var customerUpdateRequest = CustomerUpdateRequestMock.create();

        when(customerUseCase.update(any(Customer.class))).thenReturn(customer);

        var response = customerController.update(ID, customerUpdateRequest);

        verify(customerUseCase).update(customerCaptor.capture());

        assertThat(customerCaptor.getValue())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(customerUpdateRequest.toCustomerWithId(ID));

        assertThat(response)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(CustomerResponse.from(customer));
    }

}