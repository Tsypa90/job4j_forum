create table if not exists posts (
    id serial primary key,
    name varchar(2000),
    description text,
    created timestamp without time zone not null default now()
);

create table if not exists messages (
    id serial primary key,
    message text,
    post_id integer references posts(id)
);

create table if not exists posts_messages (
    post_id integer references posts(id),
    messages_id integer references messages(id)
);

create table if not exists users (
    id serial primary key,
    password varchar(225),
    username varchar(35) unique
);

create table if not exists roles (
    id serial primary key,
    name varchar(35)
);

create table if not exists users_roles (
    user_id integer references users(id),
    roles_id integer references roles(id)
);

