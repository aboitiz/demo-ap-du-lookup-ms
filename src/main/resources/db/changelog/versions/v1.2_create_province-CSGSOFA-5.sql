
 CREATE TABLE IF NOT EXISTS PUBLIC.province (
    id SERIAL NOT NULL,
    code VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "province_code_description_unique" UNIQUE ("code", "description")
);

INSERT INTO PUBLIC.province
(code, description, created_at, modified_at)
VALUES
('CEB', 'CEBU', CURRENT_TIMESTAMP, NULL),
('DAVS', 'DAVAO DEL SUR', CURRENT_TIMESTAMP, NULL),
('DAVN', 'DAVAO DEL NORTE', CURRENT_TIMESTAMP, NULL);