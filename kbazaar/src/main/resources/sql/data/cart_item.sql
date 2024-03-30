INSERT INTO cart_item (username, sku, name , price, quantity, promotion_codes, discount, total)
VALUES ('Boss', 'MOBILE-APPLE-IPHONE-12-PRO', 'Apple iPhone 12 Pro' , 20990.25, 1, '', 0, 20990.25) ON CONFLICT DO NOTHING;

INSERT INTO cart_item (username, sku, name , price, quantity, promotion_codes, discount, total)
VALUES ('Boss', 'MOBILE-SAMSUNG-GALAXY-S21-ULTRA', 'Samsung Galaxy S21 Ultra' , 18990.00, 1, '', 0, 18990.00) ON CONFLICT DO NOTHING;

INSERT INTO cart_item (username, sku, name , price, quantity, promotion_codes, discount, total)
VALUES ('Vitee', 'MOBILE-GOOGLE-PIXEL-5', 'Google Pixel 5' , 12990.75, 1, '', 0, 12990.75) ON CONFLICT DO NOTHING;