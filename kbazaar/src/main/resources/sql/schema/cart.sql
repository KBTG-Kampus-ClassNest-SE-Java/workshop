CREATE TABLE IF NOT EXISTS cart (
	id SERIAL PRIMARY KEY,
	username VARCHAR (255) UNIQUE NOT NULL,
	promotion VARCHAR (255),
	discount DECIMAL(10, 2),
	sub_total DECIMAL(10, 2),
    total_discount DECIMAL(10, 2),
    grand_total DECIMAL(10, 2)
);
