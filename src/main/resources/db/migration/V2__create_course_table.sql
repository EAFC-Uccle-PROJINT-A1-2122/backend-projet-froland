CREATE TABLE course (
  id integer primary key,
  name varchar(255) not null,
  CONSTRAINT uq_course_name UNIQUE (name)
);

CREATE TABLE section_course (
  section_id integer not null,
  course_id integer not null,
  CONSTRAINT section_course_fk FOREIGN KEY (course_id) REFERENCES course(id),
  CONSTRAINT course_section_fk FOREIGN KEY (section_id) REFERENCES section(id),
  CONSTRAINT section_course_pk PRIMARY KEY (section_id, course_id)
);
