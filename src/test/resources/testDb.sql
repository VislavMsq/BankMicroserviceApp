
CREATE TABLE IF NOT EXISTS TESTDB.PUBLIC.USERS
(
    id            uuid PRIMARY KEY,
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

CREATE TABLE IF NOT EXISTS TESTDB.PUBLIC.PRODUCTS
(
    id             uuid PRIMARY KEY,
    product_type   varchar(70),
    product_status varchar(20),
    interest_rate  decimal(6, 4),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP
);

CREATE TABLE IF NOT EXISTS TESTDB.PUBLIC.ACCOUNTS
(
    id             uuid PRIMARY KEY,
    client_id      uuid,
    account_name   varchar(40),
    account_type   varchar(28) ,
    account_status varchar(20),
    balance        decimal(15, 2),
    currency_code  varchar(3),
    bank_rating    int,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES users (id)
);
