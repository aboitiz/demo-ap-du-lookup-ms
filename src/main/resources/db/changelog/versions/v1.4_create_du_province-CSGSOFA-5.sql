
 CREATE TABLE public.province (
    id SERIAL PRIMARY KEY NOT NULL,
    code VARCHAR(255) NOT NULL,
    description text NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default NULL
 );

INSERT INTO public.province
(code, description, created_at, modified_at)
VALUES
('CEB','CEBU',CURRENT_TIMESTAMP,NULL),
('DAVS','DAVAO DEL SUR',CURRENT_TIMESTAMP,NULL),
('DAVN','DAVAO DEL NORTE',CURRENT_TIMESTAMP,NULL);