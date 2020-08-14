create table if not exists users(
    id serial primary key,
    user_name varchar(30),
    password varchar(30),
    user_type int
)