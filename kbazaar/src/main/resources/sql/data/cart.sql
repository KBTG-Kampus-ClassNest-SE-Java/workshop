INSERT INTO cart (username, discount, total_discount, promotion_codes, sub_total, grand_total)
VALUES ('Boss', 0, 0, '', 39980.25, 39980.25) ON CONFLICT DO NOTHING;

INSERT INTO cart (username, discount, total_discount, promotion_codes, sub_total, grand_total)
VALUES ('Vitee', 0, 0, '', 12990.75, 12990.75) ON CONFLICT DO NOTHING;