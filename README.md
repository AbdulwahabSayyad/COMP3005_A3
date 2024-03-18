# Project Name

This is a simple console app/script to interact with a PostgreSQL database using the JDBC API.

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Video](#video)
- [DDL](#ddl)
- [DML](#dml)

## Installation

No installation required. Just clone the repository and run A3App.java (make sure to change the constants to match your database name, port and credentials).

### Prerequisites

1. Existing postgres database that can be connected to.
2. (Optional) PGAdmin4 to run the DDL and DML if the tables/records do not exist yet. The DDL and DML scripts are provided below if needed.
3. Latest version of the JDK.

## Usage

Before running the app, change the values of the constants to match those in your system (database name, port, username, and password).

The app has 4 functions:

1. Retrieve all records from the students table
2. Insert a student record into the students table
3. Update a student record's email
4. Delete a student record

Once you run the .java file, you will be greeted with a menu. The number you input must correspond to one of the functions above which are listed in order.
To exit the app, simply enter 5.

## DDL

CREATE TABLE students
	(student_id			SERIAL,
	 first_name			VARCHAR NOT NULL,
	 last_name			VARCHAR NOT NULL,
	 email				VARCHAR NOT NULL UNIQUE,
	 enrollment_date	DATE,
	 PRIMARY KEY (student_id)
	 );

## DML

INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES
('John', 'Doe', 'john.doe@example.com', '2023-09-01'),
('Jane', 'Smith', 'jane.smith@example.com', '2023-09-01'),
('Jim', 'Beam', 'jim.beam@example.com', '2023-09-02');

## Video

This is a google drive link to the demo video: 