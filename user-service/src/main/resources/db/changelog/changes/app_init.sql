-- liquibase formatted sql
-- changeset liquibase:1

create table user_type (id bigint not null auto_increment, name varchar(255) not null, primary key (id));

create table user (id bigint not null auto_increment, user_id varchar(255) not null, password varchar(255) not null, first_name varchar(255) not null, last_name varchar(255) not null, user_type_id bigint not null, primary key (id));

alter table user add constraint FK4xtohlhi44dbeapdfdn3xah6k foreign key (user_type_id) references user_type (id);