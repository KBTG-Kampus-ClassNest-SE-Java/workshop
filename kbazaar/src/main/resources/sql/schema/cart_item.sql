CREATE TABLE IF NOT EXISTS cart_item (
    id SERIAL PRIMARY KEY,
	username VARCHAR (255) NOT NULL,
	sku VARCHAR (255) UNIQUE NOT NULL,
	name VARCHAR (255) NOT NULL,
    price DECIMAL(10, 2),
    quantity INT NOT NULL,
    promotion_codes VARCHAR (255),
	discount DECIMAL(10, 2),
    total DECIMAL(10, 2)
);
