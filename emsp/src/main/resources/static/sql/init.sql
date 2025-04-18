CREATE TABLE accounts (
                          id  bigserial PRIMARY KEY,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          status VARCHAR(50) NOT NULL DEFAULT 'CREATED',
                          contract_id VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cards (
                       id  bigserial  PRIMARY KEY,
                       uid VARCHAR(255) NOT NULL,
                       status VARCHAR(50) NOT NULL DEFAULT 'CREATED',
                       account_id int8,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
                       FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

CREATE INDEX idx_accounts_last_updated ON accounts(updated_at);
CREATE INDEX idx_cards_last_updated ON cards(updated_at);