CREATE TABLE wallets (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         user_id BIGINT NOT NULL,
                         balance DECIMAL(19, 2) NOT NULL DEFAULT 0.00,
                         currency VARCHAR(3) NOT NULL DEFAULT 'INR',
                         created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                         CONSTRAINT pk_wallets PRIMARY KEY (id),
                         CONSTRAINT uk_wallet_user UNIQUE (user_id),
                         CONSTRAINT fk_wallet_user
                             FOREIGN KEY (user_id) REFERENCES users(id),

                         CONSTRAINT chk_wallet_balance_non_negative
                             CHECK (balance >= 0)
);