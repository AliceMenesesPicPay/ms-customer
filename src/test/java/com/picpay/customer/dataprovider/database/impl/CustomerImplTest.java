package com.picpay.customer.dataprovider.database.impl;

import com.picpay.customer.dataprovider.database.entity.CustomerEntity;
import com.picpay.customer.dataprovider.database.repository.CustomerRepository;
import com.picpay.customer.util.CustomerMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.picpay.customer.util.HelpTest.ID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerImpl customerImpl;

    @Captor
    private ArgumentCaptor<CustomerEntity> customerEntityCaptor;

    @Test
    void whenFindByIdThenReturnCustomer() {
        var customer = CustomerMock.create();
        var customerEntity = CustomerEntity.from(customer);

        when(customerRepository.findById(ID)).thenReturn(Optional.of(customerEntity));

        var result = customerImpl.findById(ID);

        verify(customerRepository).findById(ID);

        assertThat(result)
                .isPresent()
                .get()
                .usingRecursiveComparison()
                .isEqualTo(customer);
    }

    @Test
    void whenSaveThenReturnCustomer() {
        var customer = CustomerMock.create();
        var customerEntity = CustomerEntity.from(customer);

        when(customerRepository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        var result = customerImpl.save(customer);

        verify(customerRepository).save(customerEntityCaptor.capture());

        assertThat(customerEntityCaptor.getValue())
                .isNotNull()
                .usingRecursiveComparison().isEqualTo(CustomerEntity.from(customer));

        assertThat(result)
                .isNotNull()
                .usingRecursiveComparison()
                .isEqualTo(customer);
    }

}