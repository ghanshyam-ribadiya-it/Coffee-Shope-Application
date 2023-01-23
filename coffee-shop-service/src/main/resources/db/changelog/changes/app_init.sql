-- liquibase formatted sql
-- changeset liquibase:1

create table coffee_shop (id bigint not null auto_increment, address_line_1 varchar(255) not null, address_line_2 varchar(255) not null, area_code varchar(255) not null, city varchar(255) not null, name varchar(255) not null, user_id bigint not null, primary key (id));

create table menu (id bigint not null auto_increment, item_name varchar(255) not null, price decimal(19,2) not null, coffee_shop_id bigint not null, primary key (id));

alter table menu add constraint FK9uc3jwb01hksmvm4hqtmcdcdd foreign key (coffee_shop_id) references coffee_shop (id);