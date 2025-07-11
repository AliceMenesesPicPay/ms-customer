package com.picpay.customer.entrypoint.api.controller;

import com.picpay.customer.core.domain.Address;
import com.picpay.customer.core.domain.Customer;
import com.picpay.customer.core.usecase.AddressUseCase;
import com.picpay.customer.core.usecase.CustomerUseCase;
import com.picpay.customer.entrypoint.api.controller.payload.response.AddressResponse;
import com.picpay.customer.util.AddressMock;
import com.picpay.customer.util.AddressRequestMock;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddressControllerTest {

    @Mock
    private AddressUseCase addressUseCase;

    @InjectMocks
    private AddressController addressController;

    @Captor
    private ArgumentCaptor<Address> addressCaptor;

    @Test
    void whenSearchByCustomerIdThenReturnAddress() {
        var address = AddressMock.create();

        when(addressUseCase.searchByCustomerId(ID)).thenReturn(address);

        var response = addressController.searchByCustomerId(ID);

        verify(addressUseCase).searchByCustomerId(ID);

        assertThat(response)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(AddressResponse.from(address));
    }

    @Test
    void whenCreateAddressThenReturnAddress() {
        var address = AddressMock.create();
        var addressRequest = AddressRequestMock.create();

        when(addressUseCase.create(eq(ID), any(Address.class))).thenReturn(address);

        var response = addressController.create(ID, addressRequest);

        verify(addressUseCase).create(eq(ID), addressCaptor.capture());

        assertThat(addressCaptor.getValue())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(addressRequest.toAddress());

        assertThat(response)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(AddressResponse.from(address));
    }

    @Test
    void whenUpdateAddressThenReturnAddress() {
        var address = AddressMock.create();
        var addressRequest = AddressRequestMock.create();

        when(addressUseCase.update(eq(ID), any(Address.class))).thenReturn(address);

        var response = addressController.update(ID, addressRequest);

        verify(addressUseCase).update(eq(ID), addressCaptor.capture());

        assertThat(addressCaptor.getValue())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(addressRequest.toAddress());

        assertThat(response)
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(AddressResponse.from(address));
    }

}