
create schema shelter;

create table shelter.roles
(
    id_role serial not null
        constraint roles_pk
            primary key,
    name    varchar
);

alter table shelter.roles
    owner to postgres;

create unique index roles_id_role_uindex
    on shelter.roles (id_role);

create table shelter.sizes
(
    id_size serial not null
        constraint sizes_pk
            primary key,
    name    varchar
);

alter table shelter.sizes
    owner to postgres;

create unique index sizes_id_size_uindex
    on shelter.sizes (id_size);

create table shelter.breeds
(
    id_breed serial not null
        constraint breeds_pk
            primary key,
    name     varchar
);

alter table shelter.breeds
    owner to postgres;

create unique index breeds_id_breed_uindex
    on shelter.breeds (id_breed);

create table shelter.animals
(
    id_animal    serial not null
        constraint animals_pk
            primary key,
    name         varchar,
    arrival_date date,
    description  varchar,
    breed_id     integer
        constraint animals_breeds_id_breed_fk
            references shelter.breeds
            on update cascade on delete set null,
    size_id      integer
        constraint animals_sizes_id_size_fk
            references shelter.sizes
            on update cascade on delete set null
);

alter table shelter.animals
    owner to postgres;

create unique index animals_id_animal_uindex
    on shelter.animals (id_animal);

create table shelter.users
(
    id_user  serial not null
        constraint users_pk
            primary key,
    email    varchar,
    password varchar,
    role_id  integer
        constraint users_roles_id_role_fk
            references shelter.roles
            on update cascade on delete set null
);

alter table shelter.users
    owner to postgres;

create unique index users_id_user_uindex
    on shelter.users (id_user);

create table shelter.interests
(
    user_id   integer
        constraint interests_users_id_user_fk
            references shelter.users
            on update cascade on delete cascade,
    animal_id integer
        constraint interests_animals_id_animal_fk
            references shelter.animals
            on update cascade on delete cascade
);

alter table shelter.interests
    owner to postgres;


