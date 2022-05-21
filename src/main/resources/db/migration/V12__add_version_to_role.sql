ALTER TABLE role ADD COLUMN version integer;

UPDATE role SET version = 0 WHERE version IS NULL;

ALTER TABLE role ALTER COLUMN version SET NOT NULL;
