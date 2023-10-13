CREATE TABLE IF NOT EXISTS users
(
    id            varchar(50) PRIMARY KEY AUTO_INCREMENT,
    tax_code      varchar(20),
    first_name    varchar(20),
    last_name     varchar(50),
    email         varchar(60),
    user_password varchar(80),
    address       varchar(20),
    phone         varchar(20),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP
);

CREATE TABLE IF NOT EXISTS products
(
    id             varchar(50) PRIMARY KEY AUTO_INCREMENT,
    product_type   varchar(70),
    product_status varchar(20),
    interest_rate  decimal(6, 4),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
);

CREATE TABLE IF NOT EXISTS accounts
(
    id             varchar(50) PRIMARY KEY AUTO_INCREMENT,
    client_id      varchar(50),
    account_name   int,
    account_type   varchar(28) UNIQUE,
    account_status varchar(20),
    balance        decimal(15, 2),
    currency_code  varchar(3),
    bank_rating    int,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES users (id)
);
