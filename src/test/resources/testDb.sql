CREATE TABLE IF NOT EXISTS USERS
(
    id            uuid PRIMARY KEY,
    tax_code      varchar(20),
    first_name    varchar(20),
    last_name     varchar(50),
    email         varchar(60),
    user_password varchar(80),
    address       varchar(50),
    phone         varchar(20),
    created_at    TIMESTAMP,
    updated_at    TIMESTAMP
);

CREATE TABLE IF NOT EXISTS EMPLOYEES
(
    id              uuid PRIMARY KEY,
    user_id         uuid,
    employee_type   varchar(40),
    employee_status varchar(28),
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES USERS (id)
    );

CREATE TABLE IF NOT EXISTS PRODUCTS
(
    id             uuid PRIMARY KEY,
    employee_id    uuid,
    product_type   varchar(70),
    product_status varchar(20),
    interest_rate  decimal(6, 4),
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (employee_id) REFERENCES EMPLOYEES (id)
);

CREATE TABLE IF NOT EXISTS ACCOUNTS
(
    id             uuid PRIMARY KEY,
    client_id      uuid,
    account_name   varchar(40),
    account_type   varchar(28),
    account_status varchar(20),
    balance        decimal(15, 2),
    currency_code  varchar(3),
    bank_rating    int,
    created_at     TIMESTAMP,
    updated_at     TIMESTAMP,
    FOREIGN KEY (client_id) REFERENCES USERS (id)
);

CREATE TABLE IF NOT EXISTS TRANSACTIONS
(
    id                      uuid PRIMARY KEY,
    debit_account_id        uuid,
    credit_account_id       uuid,
    transaction_type        varchar(40),
    amount                  decimal(12, 2),
    transaction_description varchar(28),
    updated_at              TIMESTAMP,
    FOREIGN KEY (debit_account_id) REFERENCES ACCOUNTS (id),
    FOREIGN KEY (credit_account_id) REFERENCES ACCOUNTS (id)
);

CREATE TABLE IF NOT EXISTS AGREEMENTS
(
    id               uuid PRIMARY KEY,
    account_id       uuid,
    product_id       uuid,
    manager_id       uuid,
    interest_rate    decimal(6, 4),
    currency_code    varchar(20),
    agreement_status varchar(20),
    discount         decimal(4, 2),
    agreement_limit  decimal(15, 2),
    sum              decimal(15, 2),
    created_at       TIMESTAMP,
    updated_at       TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES ACCOUNTS (id),
    FOREIGN KEY (product_id) REFERENCES PRODUCTS (id),
    FOREIGN KEY (manager_id) REFERENCES EMPLOYEES (id)
);


