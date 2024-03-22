CREATE TABLE IF NOT EXISTS promotion (
	promotion_id SERIAL PRIMARY KEY,
	code VARCHAR(255) NOT NULL UNIQUE,
	name VARCHAR(255) NOT NULL,
	description TEXT,
	start_date TIMESTAMP NOT NULL,
	end_date TIMESTAMP NOT NULL,
	discount_type VARCHAR(50) NOT NULL,
	discount_amount DECIMAL(10, 2),
	max_discount_amount DECIMAL(10, 2),
	applicable_to VARCHAR(50) NOT NULL,
	product_skus VARCHAR(255) NOT NULL DEFAULT '',
	min_quantity INT,
	free_quantity INT
);