-- Updates the `city_id` of all barangay with code starting from `DAV` and `code` = 'CAR' to 'DAV'
UPDATE "barangay" SET "city_id" = (SELECT "id" FROM "city" WHERE "code" = 'DAV')
WHERE "code" LIKE 'DAV%' AND "city_id" = (SELECT "id" FROM "city" WHERE "code" = 'CAR');

UPDATE "barangay" SET "description" = 'Panabo City' WHERE "id" IN (SELECT "id" FROM "barangay" WHERE "code" = 'PANCPAN');