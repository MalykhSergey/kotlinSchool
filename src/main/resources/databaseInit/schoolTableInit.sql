create table if not exists schools(
    id serial primary key ,
    name varchar(30) unique not null
)