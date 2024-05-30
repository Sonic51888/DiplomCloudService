create table IF NOT EXISTS netology.users (
                       id                    bigserial,
                       username              varchar(30) not null unique,
                       password              varchar(80) not null,
                       email                 varchar(50) unique,
                       primary key (id)
);

create table IF NOT EXISTS netology.roles (
                       id                    serial,
                       name                  varchar(50) not null,
                       primary key (id)
);

CREATE TABLE if not exists  netology.users_roles (
                             user_id               bigint not null,
                             role_id               int not null,
                             primary key (user_id, role_id),
                             foreign key (user_id) references netology.users (id),
                             foreign key (role_id) references netology.roles (id)
);

insert into  netology.roles (name)
values
    ('ROLE_USER'), ('ROLE_ADMIN');

insert into netology.users (login, password, email)
values
    ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'), -- 100 пароль
    ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com'); -- 100 пароль

insert into netology.users_roles (user_id, role_id)
values
    (1, 1),
    (2, 2);