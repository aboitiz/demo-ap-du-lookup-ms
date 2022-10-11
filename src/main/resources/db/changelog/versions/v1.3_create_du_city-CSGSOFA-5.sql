
 create table public.city (
    id serial primary key not null,
    city_code varchar(255) not null,
    description text not null,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default null
 );


INSERT INTO public.city
(city_code, description, created_at, modified_at)
VALUES
('BUN','Bunawan',CURRENT_TIMESTAMP,NULL),
('CAR','Carmen',CURRENT_TIMESTAMP,NULL),
('CEB','Cebu City',CURRENT_TIMESTAMP,NULL),
('CON','Consolacion',CURRENT_TIMESTAMP,NULL),
('DAV','Davao City',CURRENT_TIMESTAMP,NULL),
('DUJ','Dujali',CURRENT_TIMESTAMP,NULL),
('KAP','Kapalong',CURRENT_TIMESTAMP,NULL),
('LAS','Lasang',CURRENT_TIMESTAMP,NULL),
('LIL','Liloan',CURRENT_TIMESTAMP,NULL),
('MAL','Malabog',CURRENT_TIMESTAMP,NULL),
('MAND','Mandaue City',CURRENT_TIMESTAMP,NULL),
('MING','Minglanila',CURRENT_TIMESTAMP,NULL),
('NAGA','Naga City',CURRENT_TIMESTAMP,NULL),
('PAN','Panabo',CURRENT_TIMESTAMP,NULL),
('PAQ','Paquibato',CURRENT_TIMESTAMP,NULL),
('SFERN','San Fernando',CURRENT_TIMESTAMP,NULL),
('STOM','Sto Tomas',CURRENT_TIMESTAMP,NULL),
('TAL','Talisay',CURRENT_TIMESTAMP,NULL);
