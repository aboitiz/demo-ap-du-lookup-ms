
 create table public.du (
    id serial primary key not null,
    du_code varchar(255) not null,
    description text not null,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default null
 );


INSERT INTO public.du
(du_code, description, created_at, modified_at)
VALUES
('DLPC','Davao Light',CURRENT_TIMESTAMP,NULL),
('VECO','Visayan Electric',CURRENT_TIMESTAMP,NULL);