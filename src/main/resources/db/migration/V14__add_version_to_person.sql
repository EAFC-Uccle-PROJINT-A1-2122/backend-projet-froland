ALTER TABLE person ADD COLUMN version integer;

UPDATE person SET version = 0 WHERE version IS NULL;

ALTER TABLE person ALTER COLUMN version SET NOT NULL;
