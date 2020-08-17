create table if not exists users(
    id serial primary key,
    user_name varchar(30) not null,
    nick_name varchar(20) UNIQUE,
    password varchar(30) not null,
    school int,
    school_class int,
    user_type int not null,
    foreign key (school) references schools(id),
    foreign key (school_class) references school_classes(id)
)