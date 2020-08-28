create table if not exists school_classes_teachers(
    school_class_id bigint not null,
    teacher_id bigint not null,
    foreign key (teacher_id) references users(id) on delete cascade,
    foreign key (school_class_id) references school_classes(id) on delete cascade,
    unique (school_class_id, teacher_id)
)