CREATE TABLE section (
  id integer primary key,
  name varchar(255) not null,
  CONSTRAINT uq_section_name UNIQUE (name)
);
