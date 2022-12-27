
 CREATE TABLE PUBLIC.distribution_utility (
    id SERIAL PRIMARY KEY NOT NULL,
    code varchar(255) NOT NULL,
    description text NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default NULL
 );

INSERT INTO public.distribution_utility
(code, description, created_at, modified_at)
VALUES
('DLPC', 'Davao Light', CURRENT_TIMESTAMP, NULL),
('VECO', 'Visayan Electric', CURRENT_TIMESTAMP, NULL);