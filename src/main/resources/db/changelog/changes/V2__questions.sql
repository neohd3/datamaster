create table question
(
    id      bigserial primary key,
    created timestamp     not null,
    updated timestamp,
    survey  bigint        not null references survey (id),
    text    varchar(1024) not null,
    "order" int           not null
);