ALTER TABLE course ADD COLUMN version integer;

UPDATE course SET version = 0 WHERE version IS NULL;

ALTER TABLE course ALTER COLUMN version SET NOT NULL;
