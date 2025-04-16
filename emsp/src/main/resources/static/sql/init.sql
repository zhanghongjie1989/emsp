CREATE SEQUENCE account_auto_increase;
CREATE TYPE account_status AS  ENUM('CREATED', 'ACTIVATED', 'DEACTIVATED');
CREATE TYPE card_status AS  ENUM('CREATED', 'ASSIGNED', 'ACTIVATED', 'DEACTIVATED');
CREATE TABLE accounts (
                          id  serial PRIMARY KEY,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          status account_status NOT NULL DEFAULT 'CREATED',
                          contract_id VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cards (
                       id  serial  PRIMARY KEY,
                       emaid VARCHAR(255) NOT NULL UNIQUE,
                       uid VARCHAR(255) NOT NULL,
                       visible_number VARCHAR(255) NOT NULL,
                       status card_status NOT NULL DEFAULT 'CREATED',
                       account_id BIGINT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
                       FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

CREATE INDEX idx_accounts_last_updated ON accounts(updated_at);
CREATE INDEX idx_cards_last_updated ON cards(updated_at);