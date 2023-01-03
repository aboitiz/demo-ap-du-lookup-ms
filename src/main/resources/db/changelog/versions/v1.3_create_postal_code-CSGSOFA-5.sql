
 CREATE TABLE IF NOT EXISTS PUBLIC.postal_code (
    id SERIAL NOT NULL,
    code VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modified_at TIMESTAMP DEFAULT NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "code_description_unique" UNIQUE ("code", "description")
 );

INSERT INTO PUBLIC.postal_code
(code, description, created_at, modified_at)
VALUES
('6000', 'Cebu City', CURRENT_TIMESTAMP, NULL),
('6001', 'Consolacion', CURRENT_TIMESTAMP, NULL),
('6002', 'Liloan', CURRENT_TIMESTAMP, NULL),
('6014', 'Mandaue City', CURRENT_TIMESTAMP, NULL),
('6018', 'San Fernando', CURRENT_TIMESTAMP, NULL),
('6037', 'Naga', CURRENT_TIMESTAMP, NULL),
('6045', 'Talisay City', CURRENT_TIMESTAMP, NULL),
('6046', 'Minglanilla', CURRENT_TIMESTAMP, NULL),
('8000', 'Davao City', CURRENT_TIMESTAMP, NULL),
('8000BUN', 'Bunawan', CURRENT_TIMESTAMP, NULL),
('8000LAS', 'Lasang', CURRENT_TIMESTAMP, NULL),
('8000PAQ', 'Paquibato', CURRENT_TIMESTAMP, NULL),
('8000MAL', 'Malabog', CURRENT_TIMESTAMP, NULL),
('8101', 'Carmen', CURRENT_TIMESTAMP, NULL),
('8105', 'Panabo', CURRENT_TIMESTAMP, NULL),
('8105DUJ', 'Braulio E. Dujali', CURRENT_TIMESTAMP, NULL),
('8112', 'Santo Tomas', CURRENT_TIMESTAMP, NULL),
('8113', 'Kapalong', CURRENT_TIMESTAMP, NULL) ON CONFLICT (code, description) DO NOTHING;
