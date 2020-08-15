create table if not exists school_classes(
    id serial primary key,
    school bigint,
    name varchar(30) not null,
    foreign key (school) references schools(id) on delete cascade
)