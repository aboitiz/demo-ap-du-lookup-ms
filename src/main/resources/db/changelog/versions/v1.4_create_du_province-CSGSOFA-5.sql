
 create table public.province (
    id serial primary key not null,
    province_code varchar(255) not null,
    description text not null,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default null
 );


INSERT INTO public.province
(province_code, description, created_at, modified_at)
VALUES
('CEB','CEBU',CURRENT_TIMESTAMP,NULL),
('DAVS','DAVAO DEL SUR',CURRENT_TIMESTAMP,NULL),
('DAVN','DAVAO DEL NORTE',CURRENT_TIMESTAMP,NULL);