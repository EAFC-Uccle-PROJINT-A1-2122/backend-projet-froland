CREATE TABLE person (
  id INTEGER PRIMARY KEY,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  email_address VARCHAR(255),
  phone_number VARCHAR(255)
);

CREATE TABLE person_role (
  person_id INTEGER NOT NULL,
  role_id INTEGER NOT NULL,
  CONSTRAINT fk_person_role_person FOREIGN KEY (person_id) REFERENCES person (id),
  CONSTRAINT fk_person_role_role FOREIGN KEY (role_id) REFERENCES role (id),
  CONSTRAINT pk_person_role PRIMARY KEY (person_id, role_id)
);

ALTER TABLE attendance ADD COLUMN student_id INTEGER;
ALTER TABLE attendance ADD CONSTRAINT fk_attendance_student FOREIGN KEY (student_id) REFERENCES person (id);
