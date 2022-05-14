CREATE TABLE course_class (
  id INTEGER PRIMARY KEY,
  identifier VARCHAR(255) NOT NULL,
  academic_year_id INTEGER NOT NULL,
  course_id INTEGER NOT NULL,
  CONSTRAINT fk_course_class_academic_year FOREIGN KEY (academic_year_id) REFERENCES academic_year (id),
  CONSTRAINT fk_course_class_course FOREIGN KEY (course_id) REFERENCES course (id)
);

CREATE TABLE course_class_teacher (
  course_class_id INTEGER NOT NULL,
  teacher_id INTEGER NOT NULL,
  CONSTRAINT fk_course_class_teacher_course_class FOREIGN KEY (course_class_id) REFERENCES course_class (id),
  CONSTRAINT fk_course_class_teacher_teacher FOREIGN KEY (teacher_id) REFERENCES person (id),
  CONSTRAINT pk_course_class_teacher PRIMARY KEY (course_class_id, teacher_id)
);

CREATE TABLE course_class_student (
  course_class_id INTEGER NOT NULL,
  student_id INTEGER NOT NULL,
  CONSTRAINT fk_course_class_student_course_class FOREIGN KEY (course_class_id) REFERENCES course_class (id),
  CONSTRAINT fk_course_class_student_student FOREIGN KEY (student_id) REFERENCES person (id),
  CONSTRAINT pk_course_class_student PRIMARY KEY (course_class_id, student_id)
);
