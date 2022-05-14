CREATE TABLE academic_year (
  id integer primary key,
  beginning date not null,
  end date not null,
  CONSTRAINT uq_academic_year UNIQUE (beginning, end)
);
