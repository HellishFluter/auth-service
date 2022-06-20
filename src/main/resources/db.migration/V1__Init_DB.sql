create sequence hibernate_sequence start 1 increment 1;

create table session
(
    id         bigserial primary key,
    created_date timestamp default (now() at time zone 'utc'),
    modified_date timestamp default (now() at time zone 'utc'),
    session_id varchar,
    expire     timestamp,
    is_invalidated boolean default false
);

create table meta_info
(
    id         bigserial primary key,
    key        varchar,
    value      varchar,
    session_id int8 references session (id)
);