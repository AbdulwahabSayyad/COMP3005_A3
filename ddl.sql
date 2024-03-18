CREATE TABLE students
	(student_id			SERIAL,
	 first_name			VARCHAR NOT NULL,
	 last_name			VARCHAR NOT NULL,
	 email				VARCHAR NOT NULL UNIQUE,
	 enrollment_date	DATE,
	 PRIMARY KEY (student_id)
	 );