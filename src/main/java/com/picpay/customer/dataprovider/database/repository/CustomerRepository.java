package com.picpay.customer.dataprovider.database.repository;

import com.picpay.customer.dataprovider.database.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

}