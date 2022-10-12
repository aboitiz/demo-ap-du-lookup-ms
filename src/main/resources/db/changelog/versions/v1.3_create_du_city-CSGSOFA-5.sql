
 create table public.city (
    id serial primary key not null,
    city_code varchar(255) not null,
    description text not null,
    province_code varchar(255) not null,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default null
 );


INSERT INTO public.city
(city_code, description, province_code, created_at, modified_at)
VALUES
('BUN','Bunawan','DAVS',CURRENT_TIMESTAMP,NULL),
('CAR','Carmen','CEB',CURRENT_TIMESTAMP,NULL),
('CEB','Cebu City','CEB',CURRENT_TIMESTAMP,NULL),
('CON','Consolacion','CEB',CURRENT_TIMESTAMP,NULL),
('DAV','Davao City','DAVS',CURRENT_TIMESTAMP,NULL),
('DUJ','Dujali','DAVN',CURRENT_TIMESTAMP,NULL),
('KAP','Kapalong','DAVN',CURRENT_TIMESTAMP,NULL),
('LAS','Lasang','DAVS',CURRENT_TIMESTAMP,NULL),
('LIL','Liloan','CEB',CURRENT_TIMESTAMP,NULL),
('MAL','Malabog','DAVS',CURRENT_TIMESTAMP,NULL),
('MAND','Mandaue City','CEB',CURRENT_TIMESTAMP,NULL),
('MING','Minglanilla','CEB',CURRENT_TIMESTAMP,NULL),
('NAGA','Naga City','CEB',CURRENT_TIMESTAMP,NULL),
('PAN','Panabo','DAVN',CURRENT_TIMESTAMP,NULL),
('PAQ','Paquibato','DAVS',CURRENT_TIMESTAMP,NULL),
('SFERN','San Fernando','CEB',CURRENT_TIMESTAMP,NULL),
('STOM','Sto Tomas','DAVN',CURRENT_TIMESTAMP,NULL),
('TAL','Talisay','CEB',CURRENT_TIMESTAMP,NULL);
