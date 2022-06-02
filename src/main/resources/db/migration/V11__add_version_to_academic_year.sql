ALTER TABLE academic_year ADD COLUMN version integer;

UPDATE academic_year SET version = 0 WHERE version IS NULL;

ALTER TABLE academic_year ALTER COLUMN version SET NOT NULL;
