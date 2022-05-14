CREATE TABLE role (
  id INTEGER PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  CONSTRAINT uq_role_name UNIQUE (name)
);

CREATE TABLE role_permission (
  role_id INTEGER NOT NULL,
  permission VARCHAR(255) NOT NULL,
  CONSTRAINT pk_role_permission PRIMARY KEY (role_id, permission)
);
