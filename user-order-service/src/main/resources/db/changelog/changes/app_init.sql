-- liquibase formatted sql
-- changeset liquibase:1

create table user_type (id bigint not null auto_increment, name varchar(255) not null, primary key (id));

create table users (id bigint not null auto_increment, user_id varchar(255) not null, password varchar(255) not null, first_name varchar(255) not null, last_name varchar(255) not null, user_type_id bigint not null, primary key (id));

create table user_has_coffee_shop (id bigint not null auto_increment, address_line_1 varchar(255) not null, address_line_2 varchar(255) not null, area_code varchar(255) not null, city varchar(255) not null, name varchar(255) not null, user_id bigint not null, primary key (id));

create table menu (id bigint not null auto_increment, item_name varchar(255) not null, price decimal(19,2) not null, coffee_shop_id bigint not null, primary key (id));

create table order_history (order_id varchar(255) not null, customer_id bigint not null, coffee_shop_id bigint not null, menu_id bigint not null, price decimal(19,2) not null, quantity integer not null, total_payment decimal(19,2) not null, order_status varchar(255) not null, payment_status varchar(255) not null, created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP not null, primary key (order_id));

alter table menu add constraint FK9uc3jwb01hksmvm4hqtmcdcdd foreign key (coffee_shop_id) references user_has_coffee_shop (id);
alter table order_history add constraint FK7y0eavtssvnd45bgyomxb89ob foreign key (menu_id) references menu (id);
alter table order_history add constraint FK4voclnbr2965u9qn6c8pknive foreign key (customer_id) references users (id);
alter table order_history add constraint FKi7a6ygaru3k91h4illlkst89w foreign key (coffee_shop_id) references user_has_coffee_shop (id);
alter table user_has_coffee_shop add constraint FKt0auamqbomyok53c1vtd1a7py foreign key (user_id) references users (id);
alter table users add constraint FK4xtohlhi44dbeapdfdn3xah6k foreign key (user_type_id) references user_type (id);