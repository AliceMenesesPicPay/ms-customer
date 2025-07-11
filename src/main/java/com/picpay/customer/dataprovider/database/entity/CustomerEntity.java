package com.picpay.customer.dataprovider.database.entity;

import com.picpay.customer.core.domain.Customer;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String cpf;
    private String email;
    private String cellphone;
    private LocalDate birthday;

    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity address;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    public static CustomerEntity from(Customer customer) {
        return CustomerEntity.builder()
                .id(customer.getId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .email(customer.getEmail())
                .cellphone(customer.getCellphone())
                .birthday(customer.getBirthday())
                .address(customer.getAddress() != null ? AddressEntity.from(customer.getAddress()) : null)
                .createdAt(customer.getCreatedAt())
                .updatedAt(customer.getUpdatedAt())
                .build();
    }

    public Customer toCustomer() {
        return Customer.builder()
                .id(id)
                .name(name)
                .cpf(cpf)
                .email(email)
                .cellphone(cellphone)
                .birthday(birthday)
                .address(address != null ? address.toAddress() : null)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

}
