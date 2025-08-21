drop table if exists user_roles;
drop table if exists users;
drop table if exists roles;

-- Tabela de usuários
create table users (
    id bigserial primary key,
    login varchar(255) not null unique,
    url varchar(255)
);

-- Tabela de perfis (roles)
create table roles (
    id bigserial primary key,
    name varchar(255) not null unique
);

-- Tabela intermediária para relação N:N
create table user_roles (
    user_id bigint not null,
    role_id bigint not null,
    primary key (user_id, role_id),
    constraint fk_user_roles_user foreign key (user_id) references users (id) on delete cascade,
    constraint fk_user_roles_role foreign key (role_id) references roles (id) on delete cascade
);
