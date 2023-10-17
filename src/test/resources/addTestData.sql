INSERT INTO USERS (id, tax_code, first_name, last_name, email, user_password, address, phone, created_at, updated_at)
VALUES ('7d80f158-2eff-4328-9921-0792706fe2d5','IT678901234', 'Marco', 'Ferrari', 'marco.ferrari@gmail.com',
        '123123123123',
        '901 Via Roma, Turin, Italy', '+39 678 901 2345', '2023-09-18 12:35:00', '2023-08-20 11:55:00');

INSERT INTO USERS (id, tax_code, first_name, last_name, email, user_password, address, phone, created_at, updated_at)
VALUES ('05ebe134-0d14-4675-99ef-d07da2b2212f','IT678901244', 'Maco', 'Feri', 'maco.feri@gmail.com',
        '123',
        '901 Via Roma, Turin, Italy', '+39 678 901 2345', '2023-09-18 12:35:00', '2023-08-20 11:55:00');

INSERT INTO ACCOUNTS (id, client_id, account_name, account_type, account_status, balance , currency_code, bank_rating, created_at, updated_at)
VALUES ('1d380feb-b424-42c2-ad2b-8e593f6533bd','05ebe134-0d14-4675-99ef-d07da2b2212f', 'Anna.Petrova', 'Personal', 'Active',
        '87000.00',
        'UAH', '5', '2023-09-15 10:50:00', '2023-09-18 11:55:00');


