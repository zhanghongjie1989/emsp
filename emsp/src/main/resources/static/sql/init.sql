CREATE TABLE accounts (
                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          email VARCHAR(255) NOT NULL UNIQUE,
                          status ENUM('CREATED', 'ACTIVATED', 'DEACTIVATED') NOT NULL DEFAULT 'CREATED',
                          contract_id VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE cards (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       emaid VARCHAR(255) NOT NULL UNIQUE,
                       uid VARCHAR(255) NOT NULL,
                       visible_number VARCHAR(255) NOT NULL,
                       status ENUM('CREATED', 'ASSIGNED', 'ACTIVATED', 'DEACTIVATED') NOT NULL DEFAULT 'CREATED',
                       account_id BIGINT,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                       last_updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (account_id) REFERENCES accounts(id) ON DELETE SET NULL
);

CREATE INDEX idx_accounts_last_updated ON accounts(last_updated);
CREATE INDEX idx_cards_last_updated ON cards(last_updated);