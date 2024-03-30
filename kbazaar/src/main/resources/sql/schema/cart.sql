CREATE TABLE IF NOT EXISTS cart (
	id SERIAL PRIMARY KEY,
	username VARCHAR(255) NOT NULL,
	discount DECIMAL(10, 2) NOT NULL,
	total_discount INT NOT NULL,
	promotionCodes VARCHAR(255),
	subtotal DECIMAL(10, 2) NOT NULL ,
	grandTotal DECIMAL(10, 2) NOT NULL
);