-- liquibase formatted sql
-- changeset liquibase:1

INSERT INTO user_type (name) VALUES ('SiteAdmin');
INSERT INTO user_type (name) VALUES ('ShopAdmin');
INSERT INTO user_type (name) VALUES ('Customer');


insert into users (user_id, password, first_name, last_name, user_type_id) VALUES ('admin', 'admin123', 'Ghanshyam', 'Ribadiya', 1);
insert into users (user_id, password, first_name, last_name, user_type_id) VALUES ('Sam', 'Sam123', 'Saminesh', 'LLP', 2);
insert into users (user_id, password, first_name, last_name, user_type_id) VALUES ('Tanay', 'Tanay123', 'Tanayir', 'Shah', 3);
insert into users (user_id, password, first_name, last_name, user_type_id) VALUES ('Yours', 'Yours123', 'Shukhvani', 'Group', 2);


insert into user_has_coffee_shop (name, address_line_1, address_line_2, area_code, city, user_id) VALUES ('Sams Cafe', 'Hinjewadi', 'Phash 1', '411057', 'Pune', 2);
insert into user_has_coffee_shop (name, address_line_1, address_line_2, area_code, city, user_id) VALUES ('Yours Cafe', 'Hinjewadi', 'Phash 2', '144058', 'Pune', 4);


insert into menu (item_name, price, coffee_shop_id) VALUES ('Espresso', 100, 1);
insert into menu (item_name, price, coffee_shop_id) VALUES ('Doppio', 150, 1);
insert into menu (item_name, price, coffee_shop_id) VALUES ('Ristretto', 99.9, 2);
insert into menu (item_name, price, coffee_shop_id) VALUES ('Long Black', 49.9, 2);