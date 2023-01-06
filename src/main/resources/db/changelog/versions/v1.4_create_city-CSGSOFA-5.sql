
 CREATE TABLE IF NOT EXISTS PUBLIC.city (
    id SERIAL NOT NULL,
    code VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT NULL,
    province_id INTEGER NOT NULL,
    postal_code_id INTEGER NOT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "city_code_unique" UNIQUE ("code"),
    CONSTRAINT "city_province_fk" FOREIGN KEY ("province_id") REFERENCES "province" ("id"),
    CONSTRAINT "city_postal_code_fk" FOREIGN KEY ("postal_code_id") REFERENCES "postal_code" ("id")
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
 );

 DO $$
 	DECLARE cebId INTEGER := (SELECT "id" FROM "province" WHERE "code" = 'CEB');
 	DECLARE davsId INTEGER := (SELECT "id" FROM "province" WHERE "code" = 'DAVS');
 	DECLARE davnId INTEGER := (SELECT "id" FROM "province" WHERE "code" = 'DAVN');

    DECLARE bunawanPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8000BUN');
    DECLARE carmenPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8101');
    DECLARE cebuPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6000');
    DECLARE consolacionPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6001');
    DECLARE davaoCityPostalid INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8000');
    DECLARE dujaliPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8105DUJ');
    DECLARE kapalongPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8113');
    DECLARE lasangPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8000LAS');
    DECLARE liloanPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6002');
    DECLARE malabogPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8000MAL');
    DECLARE mandauePostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6014');
    DECLARE minglanillaPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6046');
    DECLARE nagaPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6037');
    DECLARE panaboPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8105');
    DECLARE paquibatoPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8000PAQ');
    DECLARE sanFernandoPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6018');
    DECLARE santoTomasPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '8112');
    DECLARE talisayPostalId INTEGER := (SELECT "id" FROM "postal_code" WHERE "code" = '6045');
 BEGIN
    INSERT INTO PUBLIC.city
    (code, description, province_id, postal_code_id, created_at, modified_at)
    VALUES
    ('BUN', 'Bunawan', davsId, bunawanPostalId, CURRENT_TIMESTAMP, NULL),
    ('CAR', 'Carmen', cebId, carmenPostalId, CURRENT_TIMESTAMP, NULL),
    ('CEB', 'Cebu City', cebId, cebuPostalId, CURRENT_TIMESTAMP, NULL),
    ('CON', 'Consolacion', cebId, consolacionPostalId, CURRENT_TIMESTAMP, NULL),
    ('DAV', 'Davao City', davsId, davaoCityPostalid, CURRENT_TIMESTAMP, NULL),
    ('DUJ', 'Dujali', davnId, dujaliPostalId, CURRENT_TIMESTAMP, NULL),
    ('KAP', 'Kapalong', davnId, kapalongPostalId, CURRENT_TIMESTAMP, NULL),
    ('LAS', 'Lasang', davsId, lasangPostalId, CURRENT_TIMESTAMP, NULL),
    ('LIL', 'Liloan', cebId, liloanPostalId, CURRENT_TIMESTAMP, NULL),
    ('MAL', 'Malabog', davsId, malabogPostalId, CURRENT_TIMESTAMP, NULL),
    ('MAND', 'Mandaue City', cebId, mandauePostalId, CURRENT_TIMESTAMP, NULL),
    ('MING', 'Minglanilla', cebId, minglanillaPostalId, CURRENT_TIMESTAMP, NULL),
    ('NAGA', 'Naga City', cebId, nagaPostalId, CURRENT_TIMESTAMP, NULL),
    ('PAN', 'Panabo', davnId, panaboPostalId, CURRENT_TIMESTAMP, NULL),
    ('PAQ', 'Paquibato', davsId, paquibatoPostalId, CURRENT_TIMESTAMP, NULL),
    ('SFERN', 'San Fernando', cebId, sanFernandoPostalId, CURRENT_TIMESTAMP, NULL),
    ('STOM', 'Sto Tomas', davnId, santoTomasPostalId, CURRENT_TIMESTAMP, NULL),
    ('TAL', 'Talisay', cebId, talisayPostalId, CURRENT_TIMESTAMP, NULL) ON CONFLICT (code) DO NOTHING;
 END $$;
