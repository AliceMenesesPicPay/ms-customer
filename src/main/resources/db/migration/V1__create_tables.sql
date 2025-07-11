CREATE TABLE addresses (
    id BIGSERIAL PRIMARY KEY,
    street VARCHAR(255),
    number VARCHAR(255),
    complement VARCHAR(255),
    neighborhood VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    cep VARCHAR(20),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE customers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    cpf VARCHAR(20),
    email VARCHAR(255),
    cellphone VARCHAR(20),
    birthday DATE,
    address_id BIGINT UNIQUE,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT fk_customer_address
      FOREIGN KEY(address_id)
      REFERENCES addresses(id)
);