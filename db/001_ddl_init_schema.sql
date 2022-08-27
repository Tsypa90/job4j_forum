create table posts (
  id serial primary key,
  name varchar(2000),
  description text,
  created timestamp without time zone not null default now()
);

create table users (
    id serial primary key,
    name varchar(35) unique,
    password varchar(35)
);

create table roles (
    id serial primary key,
    name varchar(25)
);

create table messages (
    id serial primary key,
    message text
);

create table posts_messages (
    post_id     integer not null
        constraint fk2ge2cx8yav1ehifrp9o0tt3jh
            references posts,
    messages_id integer not null
        constraint uk_qef1arnmivqp9q880n4sgknad
            unique
        constraint fk8h95cvvb23tlsc8r63rpj1ca1
            references messages
);

create table users_roles (
    user_id  integer not null
        constraint fk2o0jvgh89lemvvo17cbqvdxaa
            references users,
    roles_id integer not null
        constraint fka62j07k5mhgifpp955h37ponj
            references roles,
    primary key (user_id, roles_id)
);