-- liquibase formatted sql

-- changeset Carlo_Castro:1663813798083-1
CREATE TABLE if not EXISTS public.users (
    id serial primary key not null,
    address1 VARCHAR(100) NULL,
    address2 VARCHAR(100) NULL,
    inactive CHAR(1) NOT NULL,
    username VARCHAR(50) NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default null
    );


