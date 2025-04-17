CREATE TABLE accounts (
                          id  int8 PRIMARY KEY,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          status VARCHAR(50) NOT NULL DEFAULT 'CREATED',
                          contract_id VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cards (
                       id  int8  PRIMARY KEY,
                       emaid VARCHAR(255) NOT NULL UNIQUE,
                       uid VARCHAR(255) NOT NULL,
                       visible_number VARCHAR(255) NOT NULL,
                       status VARCHAR(50) NOT NULL DEFAULT 'CREATED',
                       account_id BIGINT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
                       FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

CREATE INDEX idx_accounts_last_updated ON accounts(updated_at);
CREATE INDEX idx_cards_last_updated ON cards(updated_at);