-- Tabela użytkowników
CREATE TABLE users (
                       id BIGSERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       is_archived BOOLEAN NOT NULL DEFAULT FALSE
);

-- Tabela kupujących
CREATE TABLE buyers (
                        id BIGINT PRIMARY KEY,
                        balance REAL NOT NULL,
                        delivery_address VARCHAR(255),
                        FOREIGN KEY (id) REFERENCES users(id)
);

-- Tabela sprzedawców
CREATE TABLE sellers (
                         id BIGINT PRIMARY KEY,
                         bank_account_number VARCHAR(255),
                         FOREIGN KEY (id) REFERENCES users(id)
);

-- Tabela aukcji
CREATE TABLE auctions (
                          id BIGSERIAL PRIMARY KEY,
                          title VARCHAR(255),
                          current_price NUMERIC(19, 2),
                          start_date TIMESTAMP,
                          end_date TIMESTAMP,
                          version BIGINT
);

-- Tabela ofert
CREATE TABLE bids (
                      id BIGSERIAL PRIMARY KEY,
                      amount NUMERIC(19, 2) NOT NULL,
                      buyer_id BIGINT NOT NULL,
                      auction_id BIGINT NOT NULL,
                      bid_time TIMESTAMP NOT NULL,
                      FOREIGN KEY (buyer_id) REFERENCES buyers(id),
                      FOREIGN KEY (auction_id) REFERENCES auctions(id)
);