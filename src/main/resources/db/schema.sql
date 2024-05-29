create schema netology;

create table postgres.netology.users (
    id bigserial primary key,
    login varchar(255) not null ,
    password varchar(255) not null,
    token varchar(255) )