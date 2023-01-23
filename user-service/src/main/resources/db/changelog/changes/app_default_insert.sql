-- liquibase formatted sql
-- changeset liquibase:1

INSERT INTO user_type (name) VALUES ('SiteAdmin');
INSERT INTO user_type (name) VALUES ('ShopAdmin');
INSERT INTO user_type (name) VALUES ('Customer');


insert into user (user_id, password, first_name, last_name, user_type_id) VALUES ('Admin', 'admin123', 'Ghanshyam', 'Ribadiya', 1);
insert into user (user_id, password, first_name, last_name, user_type_id) VALUES ('Sam', 'Sam123', 'Saminesh', 'LLP', 2);
insert into user (user_id, password, first_name, last_name, user_type_id) VALUES ('Tanay', 'Tanay123', 'Tanayir', 'Shah', 3);
insert into user (user_id, password, first_name, last_name, user_type_id) VALUES ('Yours', 'Yours123', 'Shukhvani', 'Group', 2);