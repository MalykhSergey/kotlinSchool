create table if not exists answers
(
    id         serial primary key,
    task_id    bigint not null,
    student_id bigint not null,
    body       varchar(200),
    foreign key (task_id) references tasks (id),
    foreign key (student_id) references users (id) on delete cascade,
    unique (task_id, student_id)
)