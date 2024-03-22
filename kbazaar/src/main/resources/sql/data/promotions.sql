INSERT INTO promotion (code, name, description, start_date, end_date, discount_type, discount_amount, applicable_to)
VALUES ('FIXEDAMOUNT10', 'Fixed Amount $10 Off Entire Cart', 'Get $10 off on your entire cart purchase.', '2024-03-25', '2024-04-25', 'FIXED_AMOUNT', 10.00, 'ENTIRE_CART') ON CONFLICT (code) DO NOTHING;

INSERT INTO promotion (code, name, description, start_date, end_date, discount_type, discount_amount, applicable_to, product_skus)
VALUES ('FIXEDAMOUNT2', 'Fixed Amount $2 Off Specific Products', 'Get $2 off on specific products.', '2024-03-25', '2024-04-25', 'FIXED_AMOUNT', 2.00, 'SPECIFIC_PRODUCTS', 'STATIONERY-STAPLER-SWINGLINE,STATIONERY-PENCIL-FABER-CASTELL') ON CONFLICT (code) DO NOTHING;

INSERT INTO promotion (code, name, description, start_date, end_date, discount_type, discount_amount, applicable_to)
VALUES ('THIRTYPERCENTOFF', '30% Discount Off Entire Cart', 'Get 30% off on your entire cart purchase.', '2024-03-25', '2024-04-25', 'PERCENTAGE', 30.00, 'ENTIRE_CART') ON CONFLICT (code) DO NOTHING;

INSERT INTO promotion (code, name, description, start_date, end_date, discount_type, discount_amount, max_discount_amount, applicable_to)
VALUES ('THIRTYPERCENTOFFLIMIT200', '30% Discount Up to 200 Baht Off Entire Cart', 'Get 30% off up to 200 Baht on your entire cart purchase.', '2024-03-25', '2024-04-25', 'PERCENTAGE', 30.00, 200.00, 'ENTIRE_CART') ON CONFLICT (code) DO NOTHING;

INSERT INTO promotion (code, name, description, start_date, end_date, discount_type, min_quantity, free_quantity, applicable_to)
VALUES ('BUY1GET1FREE', 'Buy 1 Get 1 Free', 'Buy one product and get one free.', '2024-03-25', '2024-04-25', 'buy1_get1', 1, 1,'ENTIRE_CART') ON CONFLICT (code) DO NOTHING;

INSERT INTO promotion (code, name, description, start_date, end_date, discount_type, min_quantity, free_quantity, applicable_to, product_skus)
VALUES ('BUY2GET1FREE', 'Buy 2 Get 1 Free', 'Buy two products and get one free.', '2024-03-25', '2024-04-25', 'buy2_get1', 2, 1, 'SPECIFIC_PRODUCTS', 'BEV-FANTA,BEV-RED-BULL,BEV-7UP') ON CONFLICT (code) DO NOTHING;

INSERT INTO promotion (code, name, description, start_date, end_date, discount_type, discount_amount, applicable_to, product_skus)
VALUES ('SPECIFICPRODUCT30OFF', '30% Discount on Specific Products', 'Get 30% off on specific products.', '2024-03-25', '2024-04-25', 'PERCENTAGE', 30.00, 'SPECIFIC_PRODUCTS', 'MOBILE-APPLE-IPHONE-12-PRO,STATIONERY-NOTEBOOK-MOLESKINE') ON CONFLICT (code) DO NOTHING;