INSERT INTO USERS (id, tax_code, first_name, last_name, email, user_password, address, phone, created_at, updated_at)
VALUES ('7d80f158-2eff-4328-9921-0792706fe2d5', 'IT678901234', 'Marco', 'Ferrari', 'marco.ferrari@gmail.com',
        '123123123123',
        '901 Via Roma, Turin, Italy', '+39 678 901 2345', '2023-09-18 12:35:00', '2023-08-20 11:55:00');

INSERT INTO USERS (id, tax_code, first_name, last_name, email, user_password, address, phone, created_at, updated_at)
VALUES ('05ebe134-0d14-4675-99ef-d07da2b2212f', 'GB123456789', 'John', 'Smith',
        'john.smith@gmail.com',
        '98765676766546', '123 Main Street, London, UK', '+44 123 456 7890', '2023-09-18 12:12:00',
        '2023-08-20 11:30:00');

INSERT INTO EMPLOYEES (id, user_id, employee_type, employee_status, created_at, updated_at)
VALUES ('5b92d682-015a-4a12-9e25-33daa97ebdec', '05ebe134-0d14-4675-99ef-d07da2b2212f', 'LoanManager', 'Free',
        '2023-09-18 12:12:00', '2023-08-20 11:30:00');

INSERT INTO ACCOUNTS (id, client_id, account_name, account_type, account_status, balance, currency_code, bank_rating,
                      created_at, updated_at)
VALUES ('11ebe124-0d14-4675-99ef-d07da2b2222a', '05ebe134-0d14-4675-99ef-d07da2b2212f', 'John.Smith', 'Personal',
        'Active',
        '12250.00', 'USD', '5', '2023-09-18 12:12:00', '2023-08-20 11:30:00');

INSERT INTO TRANSACTIONS (id, debit_account_id, credit_account_id, transaction_type, amount, transaction_description,
                          updated_at)
VALUES ('06752bb6-809f-400a-afff-50aaf6ccd647', '11ebe124-0d14-4675-99ef-d07da2b2222a',
        '11ebe124-0d14-4675-99ef-d07da2b2222a', 'TransferFunds',
        '0.00',
        'for cookies', '2023-08-22 08:10:00');

INSERT INTO PRODUCTS (id, product_type, product_status, interest_rate, created_at, updated_at)
VALUES ('60587cf0-aaa3-475a-8ef8-aa452c6e8fe9', 'HealthInsurance', 'Available', '2.9900', '2023-09-18 12:12:00',
        '2023-08-20 11:30:00');

INSERT INTO AGREEMENTS (id, account_id, product_id, manager_id, interest_rate, currency_code, agreement_status,
                        discount, agreement_limit, sum, created_at, updated_at)
VALUES ('a242f83f-a341-45f4-9430-e43cfbf55361', '11ebe124-0d14-4675-99ef-d07da2b2222a',
        '60587cf0-aaa3-475a-8ef8-aa452c6e8fe9',
        '5b92d682-015a-4a12-9e25-33daa97ebdec', '10.2000', 'USD', 'Completed', '0.0', '5000.00', '500.0',
        '2023-01-28 08:41:21',
        '2023-08-12 12:55:12');









