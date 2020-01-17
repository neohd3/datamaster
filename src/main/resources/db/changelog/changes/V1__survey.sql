create table survey
(
    id      bigserial primary key,
    created timestamp    not null,
    updated timestamp,
    name    varchar(128) not null,
    start   timestamp    not null,
    "end"   timestamp    not null,
    active  boolean      not null
);

create unique index survey_name_uindex
    on survey (name);