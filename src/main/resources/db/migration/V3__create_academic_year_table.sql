CREATE TABLE academic_year (
  id integer primary key,
  beginning_date date not null,
  end_date date not null,
  CONSTRAINT uq_academic_year UNIQUE (beginning_date, end_date)
);
