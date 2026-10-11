-- Tabela użytkowników
CREATE TABLE users (
                       id BIGINT PRIMARY KEY,
                       login VARCHAR(255) NOT NULL
);

CREATE TABLE buyers (
                        id BIGINT PRIMARY KEY,
                        delivery_address VARCHAR(255),
                        FOREIGN KEY (id) REFERENCES users(id)
);

CREATE TABLE sellers (
                         id BIGINT PRIMARY KEY,
                         bank_account_number VARCHAR(255),
                         FOREIGN KEY (id) REFERENCES users(id)
);

-- Tabela aukcji z kolumną dla OPTIMISTIC BLOCK
CREATE TABLE auctions (
                          id BIGINT PRIMARY KEY,
                          title VARCHAR(255),
                          current_price NUMERIC(19, 2),
                          version BIGINT NOT NULL -- Kolumna pod adnotację @Version (wymóg ACID i współbieżności)
);

-- Tabela ofert z relacją ManyToOne do aukcji
CREATE TABLE bids (
                      id BIGINT PRIMARY KEY,
                      amount NUMERIC(19, 2),
                      buyer_id BIGINT,
                      auction_id BIGINT,
                      FOREIGN KEY (buyer_id) REFERENCES buyers(id),
                      FOREIGN KEY (auction_id) REFERENCES auctions(id)
);