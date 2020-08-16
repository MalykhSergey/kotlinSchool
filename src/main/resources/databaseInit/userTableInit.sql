create table if not exists users(
    id serial primary key,
    user_name varchar(30),
    nick_name varchar(20),
    password varchar(30),
    school bigint,
    user_type int,
    foreign key (school) references schools(id)
)