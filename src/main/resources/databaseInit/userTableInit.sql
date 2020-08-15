create table if not exists users_tokens(
    id serial primary key,
    user_name varchar(30),
    password varchar(30),
    user_type int,
    user_id bigint not null  unique
)