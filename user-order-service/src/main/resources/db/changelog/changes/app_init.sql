-- liquibase formatted sql
-- changeset liquibase:1

create table orders (order_id varchar(255) not null, customer_id bigint not null, coffee_shop_id bigint not null, menu_id bigint not null, price decimal(19,2) not null, quantity integer not null, total_payment decimal(19,2) not null, order_status varchar(255) not null, payment_status varchar(255) not null, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null, primary key (order_id));