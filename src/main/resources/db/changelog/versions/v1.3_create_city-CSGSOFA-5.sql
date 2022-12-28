
 CREATE TABLE IF NOT EXISTS PUBLIC.city (
    id SERIAL NOT NULL,
    code VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT NULL,
    province_id INTEGER NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "city_code_unique" UNIQUE ("code"),
    CONSTRAINT "province_fk" FOREIGN KEY ("province_id") REFERENCES "province" ("id")
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
 );

 DO $$
 	DECLARE cebId INTEGER := (SELECT "id" FROM "province" WHERE "code" = 'CEB');
 	DECLARE davsId INTEGER := (SELECT "id" FROM "province" WHERE "code" = 'DAVS');
 	DECLARE davnId INTEGER := (SELECT "id" FROM "province" WHERE "code" = 'DAVN');
 BEGIN
    INSERT INTO PUBLIC.city
    (code, description, province_id, created_at, modified_at)
    VALUES
    ('BUN', 'Bunawan', davsId, CURRENT_TIMESTAMP, NULL),
    ('CAR', 'Carmen', cebId, CURRENT_TIMESTAMP, NULL),
    ('CEB', 'Cebu City', cebId, CURRENT_TIMESTAMP, NULL),
    ('CON', 'Consolacion', cebId, CURRENT_TIMESTAMP, NULL),
    ('DAV', 'Davao City', davsId, CURRENT_TIMESTAMP, NULL),
    ('DUJ', 'Dujali', davnId, CURRENT_TIMESTAMP, NULL),
    ('KAP', 'Kapalong', davnId, CURRENT_TIMESTAMP, NULL),
    ('LAS', 'Lasang', davsId, CURRENT_TIMESTAMP, NULL),
    ('LIL', 'Liloan', cebId, CURRENT_TIMESTAMP, NULL),
    ('MAL', 'Malabog', davsId, CURRENT_TIMESTAMP, NULL),
    ('MAND', 'Mandaue City', cebId, CURRENT_TIMESTAMP, NULL),
    ('MING', 'Minglanilla', cebId, CURRENT_TIMESTAMP, NULL),
    ('NAGA', 'Naga City', cebId, CURRENT_TIMESTAMP, NULL),
    ('PAN', 'Panabo', davnId, CURRENT_TIMESTAMP, NULL),
    ('PAQ', 'Paquibato', davsId, CURRENT_TIMESTAMP, NULL),
    ('SFERN', 'San Fernando', cebId, CURRENT_TIMESTAMP, NULL),
    ('STOM', 'Sto Tomas', davnId, CURRENT_TIMESTAMP, NULL),
    ('TAL', 'Talisay', cebId, CURRENT_TIMESTAMP, NULL) ON CONFLICT (code) DO NOTHING;
 END $$;
