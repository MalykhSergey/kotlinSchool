create table if not exists tasks
(
    id           serial primary key,
    title        varchar(30),
    body         varchar,
    school_class int,
    teacher      bigint,
    foreign key (school_class) references school_classes (id) on delete cascade,
    foreign key (teacher) references users (id) on delete cascade
)