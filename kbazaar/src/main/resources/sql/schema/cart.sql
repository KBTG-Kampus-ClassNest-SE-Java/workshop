CREATE TABLE IF NOT EXISTS cart (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    discount DECIMAL(10, 2) default 0.00,
    total_discount DECIMAL(10, 2) default 0.00,
    promotion_codes VARCHAR(255) default '', -- comma-separated list of promotion codes
    subtotal DECIMAL(10, 2) default 0.00,
    grand_total DECIMAL(10, 2) default 0.00
);
