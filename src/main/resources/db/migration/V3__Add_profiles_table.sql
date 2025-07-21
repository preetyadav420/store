create table profiles
(
    id             bigint           not null
        primary key,
    bio            varchar(255)     null,
    phone_number   numeric          null,
    date_of_birth  DATE             null,
    loyalty_points bigint default 0 null,
    constraint profiles_users_id_fk
        foreign key (id) references users (id)
);