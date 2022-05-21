ALTER TABLE course_class ADD COLUMN version integer;

UPDATE course_class SET version = 0 WHERE version IS NULL;

ALTER TABLE course_class ALTER COLUMN version SET NOT NULL;
