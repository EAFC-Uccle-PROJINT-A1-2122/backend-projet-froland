CREATE TABLE attendance (
  id INTEGER PRIMARY KEY,
  session_id INTEGER NOT NULL,
  status VARCHAR(255) NOT NULL,
  presence_periods INTEGER,
  CONSTRAINT attendance_session FOREIGN KEY (session_id) REFERENCES session (id)
);
