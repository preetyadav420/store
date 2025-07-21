create table user_tag
(
    user_id bigint not null,
    tag_id  bigint not null,
    constraint user_tag_pk
        primary key (user_id, tag_id),
    constraint user_tag_tags_id_fk
        foreign key (tag_id) references tags (id) on delete cascade ,
    constraint user_tag_users_id_fk
        foreign key (user_id) references users (id) on delete cascade
);
