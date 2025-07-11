package com.picpay.customer.core.usecase;

import com.picpay.customer.core.domain.Address;
import com.picpay.customer.core.domain.Customer;
import com.picpay.customer.core.exception.AddressNotFoundException;
import com.picpay.customer.util.AddressMock;
import com.picpay.customer.util.CustomerMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.picpay.customer.util.HelpTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddressUseCaseTest {

    @Mock
    private CustomerUseCase customerUseCase;

    @InjectMocks
    private AddressUseCase addressUseCase;

    @Captor
    private ArgumentCaptor<Address> addressCaptor;

    @Captor
    private ArgumentCaptor<Customer> customerCaptor;

    @Test
    void whenSearchByCustomerIdThenReturnAddress() {
        var address = AddressMock.create();
        var customer = CustomerMock.create();
        customer.addAddress(address);

        when(customerUseCase.searchById(ID)).thenReturn(customer);

        var result = addressUseCase.searchByCustomerId(ID);

        verify(customerUseCase).searchById(ID);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(address);
    }

    @Test
    void whenSearchByCustomerIdWithoutAddressThenThrowException() {
        var customer = CustomerMock.create();

        when(customerUseCase.searchById(ID)).thenReturn(customer);

        assertThatThrownBy(() -> addressUseCase.searchByCustomerId(ID))
                .isInstanceOf(AddressNotFoundException.class)
                .hasMessageContaining(ID.toString());
    }

    @Test
    void whenCreateThenReturnAddress() {
        var address = AddressMock.create();
        var customer = CustomerMock.create();
        var customerWithAddress = CustomerMock.create();
        customerWithAddress.addAddress(address);

        when(customerUseCase.searchById(ID)).thenReturn(customer);
        when(customerUseCase.save(any(Customer.class))).thenReturn(customerWithAddress);

        var result = addressUseCase.create(ID, address);

        verify(customerUseCase).searchById(ID);
        verify(customerUseCase).save(customerCaptor.capture());

        assertThat(customerCaptor.getValue().getAddress()).usingRecursiveComparison().isEqualTo(address);
        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(address);
    }

    @Test
    void whenUpdateThenReturnAddress() {
        var address = AddressMock.create();
        var addressWithOtherData = AddressMock.createWithOtherData();

        var customer = CustomerMock.create();
        customer.addAddress(address);

        var customerWithOtherAddress = CustomerMock.create();
        customerWithOtherAddress.addAddress(addressWithOtherData);

        when(customerUseCase.searchById(customerWithOtherAddress.getId())).thenReturn(customer);
        when(customerUseCase.save(any(Customer.class))).thenReturn(customerWithOtherAddress);

        var result = addressUseCase.update(ID, addressWithOtherData);

        verify(customerUseCase).searchById(ID);
        verify(customerUseCase).save(customerCaptor.capture());

        assertThat(customerCaptor.getValue().getAddress())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(addressWithOtherData);

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(addressWithOtherData);
    }

    @Test
    void whenUpdateWithoutAddressThenThrowException() {
        var address = AddressMock.create();
        var customer = CustomerMock.create();

        when(customerUseCase.searchById(ID)).thenReturn(customer);

        assertThatThrownBy(() -> addressUseCase.update(ID, address))
                .isInstanceOf(AddressNotFoundException.class)
                .hasMessageContaining(ID.toString());

        verify(customerUseCase, never()).save(any(Customer.class));
    }

}