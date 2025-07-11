package com.picpay.customer.core.usecase;

import com.picpay.customer.core.domain.AccountType;
import com.picpay.customer.core.domain.Customer;
import com.picpay.customer.core.exception.CreateAccountException;
import com.picpay.customer.core.exception.CustomerNotFoundException;
import com.picpay.customer.core.gateway.CustomerGateway;
import com.picpay.customer.core.gateway.FinancesGateway;
import com.picpay.customer.dataprovider.database.entity.CustomerEntity;
import com.picpay.customer.dataprovider.integration.finances.exception.FinancesIntegrationException;
import com.picpay.customer.util.AccountMock;
import com.picpay.customer.util.CustomerMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.picpay.customer.core.domain.AccountType.CHECKING;
import static com.picpay.customer.core.domain.AccountType.SAVINGS;
import static com.picpay.customer.util.HelpTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerUseCaseTest {

    @Mock
    private CustomerGateway customerGateway;

    @Mock
    private FinancesGateway financesGateway;

    @InjectMocks
    private CustomerUseCase customerUseCase;

    @Captor
    private ArgumentCaptor<Customer> customerCaptor;

    @Test
    void whenSearchByIdThenReturnCustomer() {
        var customer = CustomerMock.create();

        when(customerGateway.findById(ID)).thenReturn(Optional.of(customer));

        var result = customerUseCase.searchById(ID);

        verify(customerGateway).findById(ID);

        assertThat(result).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    void whenSearchByIdNotFoundThenThrowException() {
        when(customerGateway.findById(ID)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> customerUseCase.searchById(ID))
                .isInstanceOf(CustomerNotFoundException.class)
                .hasMessageContaining(ID.toString());
    }

    @Test
    void whenCreateThenReturnCustomerWithAccount() {
        var customer = CustomerMock.create();
        var customerSaved = CustomerMock.create();
        var account = List.of(AccountMock.create(CHECKING), AccountMock.create(SAVINGS));

        when(customerGateway.save(any(Customer.class))).thenReturn(customerSaved);
        when(financesGateway.createAccount(customerSaved.getId())).thenReturn(account);

        var result = customerUseCase.create(customer);

        verify(customerGateway).save(customer);
        verify(financesGateway).createAccount(customerSaved.getId());

        assertThat(result.getAccounts())
                .isNotEmpty()
                .hasSize(2)
                .isEqualTo(account);
    }

    @Test
    void whenCreateAndThrowFinancesIntegrationExceptionThenThrowCreateAccountException() {
        var customer = CustomerMock.create();
        var customerSaved = CustomerMock.create();

        when(customerGateway.save(any(Customer.class))).thenReturn(customerSaved);
        when(financesGateway.createAccount(customerSaved.getId())).thenThrow(FinancesIntegrationException.class);

        assertThatThrownBy(() -> customerUseCase.create(customer))
                .isInstanceOf(CreateAccountException.class)
                .hasMessageContaining(ID.toString());
    }

    @Test
    void whenUpdateThenReturnUpdatedCustomer() {
        var customer = CustomerMock.createWithOtherData();
        var existingCustomer = CustomerMock.create();

        when(customerGateway.findById(customer.getId())).thenReturn(Optional.of(existingCustomer));
        when(customerGateway.save(any(Customer.class))).thenReturn(customer);

        var result = customerUseCase.update(customer);

        verify(customerGateway).findById(customer.getId());
        verify(customerGateway).save(customerCaptor.capture());

        assertThat(customerCaptor.getValue())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(customer);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    void whenSaveThenReturnCustomer() {
        var customer = CustomerMock.create();

        when(customerGateway.save(customer)).thenReturn(customer);

        var result = customerUseCase.save(customer);

        verify(customerGateway).save(customer);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(customer);
    }

}