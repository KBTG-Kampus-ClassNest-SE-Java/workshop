CREATE TABLE IF NOT EXISTS cart_item (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    sku VARCHAR(255) NOT NULL default '',
    name VARCHAR(255) NOT NULL default '',
    price DECIMAL(10, 2) default 0.00,
    quantity INT NOT NULL default 0,
    discount DECIMAL(10, 2) default 0.00,
    promotion_codes VARCHAR(255) NOT NULL default '', -- comma-separated list of promotion codes
    UNIQUE (username, sku)
);
