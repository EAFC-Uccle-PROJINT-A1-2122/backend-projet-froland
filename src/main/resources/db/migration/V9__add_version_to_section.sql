ALTER TABLE section ADD COLUMN version integer;

UPDATE section SET version = 0 WHERE version IS NULL;

ALTER TABLE section ALTER COLUMN version SET NOT NULL;
