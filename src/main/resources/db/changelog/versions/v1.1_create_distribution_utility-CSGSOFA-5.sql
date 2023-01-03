
 CREATE TABLE IF NOT EXISTS PUBLIC.distribution_utility (
    id SERIAL NOT NULL,
    code VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    created_at TIMESTAMP default CURRENT_TIMESTAMP,
    modified_at TIMESTAMP default NULL,
    PRIMARY KEY ("id"),
    CONSTRAINT "distribution_utility_code_unique" UNIQUE ("code")
 );

INSERT INTO PUBLIC.distribution_utility
(code, description, created_at, modified_at)
VALUES
('DLPC', 'Davao Light', CURRENT_TIMESTAMP, NULL),
('VECO', 'Visayan Electric', CURRENT_TIMESTAMP, NULL);