INSERT INTO product (price, quantity, name, sku) VALUES (20.22, 999, 'iPhone 15 Pro Max', 'SKU001') ON CONFLICT DO NOTHING;
INSERT INTO product (price, quantity, name, sku) VALUES (33.33, 50, 'S24 Ultra', 'SKU002') ON CONFLICT DO NOTHING;
INSERT INTO product (price, quantity, name, sku) VALUES (5.99, 200, 'OnePlus', 'SKU003') ON CONFLICT DO NOTHING;
INSERT INTO product (price, quantity, name, sku) VALUES (8.99, 200, 'iPad', 'SKU004') ON CONFLICT DO NOTHING;
INSERT INTO product (price, quantity, name, sku) VALUES (10.99, 399, 'iPad Pro', 'SKU005') ON CONFLICT DO NOTHING;

