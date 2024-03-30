CREATE TABLE IF NOT EXISTS cart_item (
	username VARCHAR (255) UNIQUE NOT NULL,
	sku VARCHAR (255) UNIQUE NOT NULL,
    price DECIMAL(10, 2),
    quantity INT NOT NULL,
    promotion VARCHAR (255),
	discount DECIMAL(10, 2),
    total DECIMAL(10, 2)
);
