Student Management System

A console-based Student Management System developed in Java with JDBC and MySQL for persistent student record management.

The application allows users to add, view, search, update, and delete student records directly from the command line. Student information is stored in a MySQL database, while Java handles the application logic and database communication through JDBC.

📌 Table of Contents
Overview
Features
Technology Stack
Project Architecture
Student Information
Database Structure
Project Structure
How the Application Works
CRUD Operations
Validation
JDBC Concepts Used
Prerequisites
Database Setup
Configuration
Running the Application
Application Menu
Example Workflow
Error Handling
Testing
Known Limitations
Future Improvements
Learning Objectives
Author
📖 Overview

The Student Management System is a Java-based command-line application designed to manage student records using a relational database.

Instead of keeping student information only in Java's memory, the application connects to a MySQL database using JDBC (Java Database Connectivity).

The system performs database operations such as:

Creating student records
Reading student records
Updating existing records
Deleting student records
Searching for individual students
Checking duplicate student IDs
Checking duplicate class roll numbers
Validating user input

The application follows a simple separation between the student data model, record-management logic, and the application's entry point.

✨ Features
Student Management

The system supports the following operations:

1. Add Student

Users can enter information for a new student and store it in the database.

Before insertion, the system checks whether the student ID already exists.

Student information includes:

Student ID
Class Roll Number
Name
Age
Academic Year
Branch
Section
Department
CGPA

The application uses a PreparedStatement to insert the record into MySQL.

2. Display Students

The application retrieves student records from the StudentDetails table and displays them through the console.

The system executes a SQL SELECT query and processes the returned ResultSet.

3. Search Student

A student can be searched using their unique ID.

The application executes:

SELECT * FROM StudentDetails WHERE ID = ?

The ID is supplied through a PreparedStatement, and the returned record is displayed if it exists.

4. Remove Student

Students can be deleted using their ID.

The application executes a parameterized DELETE query:

DELETE FROM StudentDetails WHERE ID = ?

The number of affected rows is checked to determine whether the deletion was successful.

5. Edit Student

The application provides an edit menu that allows individual student fields to be modified.

The available fields are:

ID
Roll Number
Name
Age
Academic Year
Branch
Section
Department
CGPA

The implementation uses an updatable JDBC ResultSet to modify the selected record.

🛠 Technology Stack
Technology	Purpose
Java	Application development
JDBC	Database connectivity
MySQL	Persistent data storage
Maven	Project/build and dependency management
IntelliJ IDEA	Development environment
Git & GitHub	Version control and source-code hosting
🏗 Project Architecture

The application follows a simple layered structure:

User
│
▼
Main
│
▼
StudentRecord
│
├── Add Student
├── Display Students
├── Search Student
├── Remove Student
└── Update Student
│
▼
JDBC
│
▼
MySQL Database
│
▼
StudentDetails Table

The StudentRecord class receives a JDBC Connection and uses it for database operations.

👨‍🎓 Student Information

Each student record contains the following information:

Field	Description
ID	Unique student identifier
ClassRoll	Student's class roll number
Name	Student's name
Age	Student's age
Year	Academic year
Branch	Engineering/academic branch
Section	Student section
Department	Academic department
CGPA	Student's cumulative grade point average
🗄 Database Structure

The application uses a MySQL database named:

Students

The primary table used by the application is:

StudentDetails

Conceptually, the table contains:

StudentDetails
│
├── ID
├── ClassRoll
├── Name
├── Age
├── Year
├── Branch
├── Section
├── Department
└── CGPA

The Java application interacts with this table using JDBC queries.

📁 Project Structure

A typical Maven project structure is:

Student-Management-System/
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── ...
│   │
│   └── test/
│       └── java/
│
├── pom.xml
│
└── README.md

The main application logic is divided into classes responsible for:

Starting the application
Representing student data
Managing student records
Connecting to the database
⚙️ How the Application Works
1. Establishing Database Connection

The application establishes a JDBC connection with MySQL.

The connection is then passed to the StudentRecord object so database operations can be performed using the same connection.

Main
│
├── Create JDBC Connection
│
└── Create StudentRecord
│
└── Use Connection
2. Receiving User Input

The application uses Java's Scanner to receive information from the command line.

For example:

ENTER ID OF THE STUDENT :
ENTER NAME OF THE STUDENT :
ENTER AGE OF THE STUDENT :

The entered values are stored in a Student object before being inserted into the database.

3. Executing SQL

Database operations are performed through JDBC.

For parameterized queries, the application uses:

PreparedStatement

For example:

String sql =
"INSERT INTO StudentDetails" +
"(ID, ClassRoll, Name, Age, Year, Branch, Section, Department, CGPA)" +
" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

Values are then supplied using methods such as:

query.setString(...);
query.setInt(...);
query.setDouble(...);

Finally:

query.executeUpdate();

is used to execute the insertion.

🔄 CRUD Operations

CRUD stands for:

C → Create
R → Read
U → Update
D → Delete

The Student Management System implements all four.

Create
INSERT INTO StudentDetails (...)
VALUES (...);

Used when adding a new student.

Read
SELECT * FROM StudentDetails;

Used for displaying student records.

The application also supports searching:

SELECT * FROM StudentDetails
WHERE ID = ?;
Update

The application updates individual student fields through an updatable ResultSet.

Examples include:

Update ID
Update Roll Number
Update Name
Update Age
Update Year
Update Branch
Update Section
Update Department
Update CGPA
Delete
DELETE FROM StudentDetails
WHERE ID = ?;

Used to remove a student record.

🔐 Duplicate Record Checks

The system performs checks before inserting or modifying student information.

Duplicate Student ID

The application retrieves existing IDs from the database and compares them against the ID entered by the user.

If the ID already exists:

ID Already Assigned

is displayed.

Duplicate Class Roll Number

The application checks class roll numbers using:

SELECT ClassRoll
FROM StudentDetails
WHERE Section = ?
AND Year = ?
AND Branch = ?;

This allows roll-number uniqueness to be checked within the relevant academic grouping.

🧪 Validation

The application validates user input for several fields.

Examples include handling invalid input for:

Age
Roll number
Academic year
CGPA

Java's:

InputMismatchException

is used to handle cases where the user enters an unexpected data type.

For example, entering text when an integer is expected can be detected and handled instead of allowing the application to terminate unexpectedly.

🔌 JDBC Concepts Used

This project provides practical experience with several important JDBC concepts.

JDBC Connection

Used to connect Java with MySQL.

Connection
Statement

Used for SQL statements that do not require parameters.

Statement
PreparedStatement

Used for parameterized SQL queries.

PreparedStatement

This is used extensively for insertion, searching, deletion, and other parameterized operations.

ResultSet

Used to process data returned from a SELECT query.

ResultSet

For example:

while(table.next()) {
...
}
executeQuery()

Used for queries that return records.

query.executeQuery();

Typical use:

SELECT
executeUpdate()

Used for operations that modify database records.

query.executeUpdate();

Typical use:

INSERT
UPDATE
DELETE
Try-With-Resources

The project uses try-with-resources for JDBC objects so that resources can be automatically closed.

Example:

try (PreparedStatement query =
connection.prepareStatement(sql)) {

    // database operation

}
🔒 Why PreparedStatement?

Parameterized queries are used instead of constructing SQL strings directly with user input.

For example:

String sql = "DELETE FROM StudentDetails WHERE ID = ?";
PreparedStatement query = connection.prepareStatement(sql);
query.setString(1, studentID);

This provides a cleaner way to pass values to SQL and helps protect against SQL injection compared with directly concatenating user input into SQL statements.

📋 Application Menu

The main application provides a menu similar to:

=================================
STUDENT MANAGEMENT SYSTEM
=================================

1. ADD STUDENT
2. DISPLAY STUDENTS
3. REMOVE STUDENT
4. SEARCH STUDENT
5. EDIT STUDENT
6. EXIT

The user selects an operation and the corresponding method is executed.

▶️ Example Workflow
Add Student
ENTER ID OF THE STUDENT : ST101
ENTER ROLL NUMBER : 15
ENTER NAME : Safwan Baig
ENTER AGE : 19
ENTER ACADEMIC YEAR : 2
ENTER BRANCH : AIDS
ENTER SECTION : A
ENTER DEPARTMENT : AI & DS
ENTER CGPA : 8.5

The application checks the ID and then inserts the student into MySQL.

Search Student
ENTER ID OF STUDENT : ST101

ID : ST101
ROLL : 15
NAME : Safwan Baig
AGE : 19
ACADEMIC YEAR : 2
BRANCH : AIDS
SECTION : A
DEPARTMENT : AI & DS
CGPA : 8.5
Delete Student
ENTER ID OF THE STUDENT : ST101

Student Record Deleted Successfully

If the ID does not exist:

Student Not Found!!

The implementation determines this using the number of rows affected by the DELETE query.

⚠️ Error Handling

The application handles several types of errors.

SQL Exceptions

Database-related errors are handled using:

SQLException

This prevents database failures from immediately crashing the application without an error message.

Invalid User Input

Invalid numeric input is handled using:

InputMismatchException

This is particularly relevant when reading values such as:

Age
Roll Number
Year
CGPA
🧪 Testing

JUnit can be used to test individual application components independently from the complete application flow.

Potential test cases include:

✓ Valid student information
✓ Invalid student information
✓ Duplicate student ID
✓ Duplicate roll number
✓ Searching existing student
✓ Searching non-existing student
✓ Deleting existing student
✓ Deleting non-existing student
✓ Updating student information

For a database-backed application, integration tests can additionally verify that operations actually modify the MySQL database as expected.

⚠️ Known Limitations

This project is intentionally a console-based JDBC practice application, so it has several limitations.

1. Console Interface

The application does not currently have a graphical or web interface.

2. Limited Validation

The current validation primarily focuses on input type and duplicate values. More comprehensive validation could be added for constraints such as:

Valid age range
Valid CGPA range
Valid academic year
Valid branch
Valid section
3. Database Credentials

Database credentials should not be hard-coded directly into source code in a production application.

A better approach would be to use:

Environment Variables
or
Configuration Files
or
Secret Management
4. Single-User CLI Design

The application is designed for local command-line use rather than concurrent multi-user access.

5. Basic Architecture

The current application is suitable for learning JDBC and database interaction but is not intended to represent a production enterprise architecture.

🚀 Future Improvements

Possible future improvements include:

Application
Add graphical user interface
Convert the application into a web application
Add login/authentication
Add role-based access
Add student sorting
Add advanced searching/filtering
Add pagination
Add student attendance management
Add subject/marks management
Database
Add database indexes
Add stronger constraints
Normalize related data into multiple tables
Add transactions
Add foreign-key relationships
Improve query efficiency
Backend
Introduce DAO pattern
Introduce Service layer
Use connection pooling
Add centralized exception handling
Add logging
Add comprehensive unit and integration tests
Deployment

Eventually the project could be migrated toward:

Java
↓
Spring Boot
↓
REST API
↓
MySQL
↓
Web / React Frontend
🎯 Learning Objectives

This project was primarily developed to gain practical experience with:

Java programming
Object-oriented programming
JDBC
MySQL
SQL CRUD operations
PreparedStatement
ResultSet
Database connections
Exception handling
Input validation
Maven
Unit testing
Git and GitHub

The project demonstrates the transition from Java-only applications to database-integrated applications.

🔄 Application Flow

The overall flow can be summarized as:

                 ┌──────────────────┐
                 │      User        │
                 └────────┬─────────┘
                          │
                          ▼
                 ┌──────────────────┐
                 │      Main        │
                 │   Menu System    │
                 └────────┬─────────┘
                          │
             ┌────────────┼────────────┐
             │            │            │
             ▼            ▼            ▼
          Add/View     Search/Edit   Delete
             │            │            │
             └────────────┼────────────┘
                          ▼
                 ┌──────────────────┐
                 │  StudentRecord   │
                 └────────┬─────────┘
                          │
                          ▼
                 ┌──────────────────┐
                 │       JDBC       │
                 └────────┬─────────┘
                          │
                          ▼
                 ┌──────────────────┐
                 │      MySQL       │
                 │ StudentDetails   │
                 └──────────────────┘
🧰 Prerequisites

Before running the project, make sure the following are installed:

Java JDK
Maven
MySQL Server
MySQL Workbench (optional but recommended)
Git (optional, for cloning the repository)

Verify Java:

java -version

Verify Maven:

mvn -version
🗄 Database Setup

Create the database:

CREATE DATABASE Students;

Select the database:

USE Students;

Create the student table according to the fields used by the application.

Example structure:

CREATE TABLE StudentDetails (
ID VARCHAR(50) PRIMARY KEY,
ClassRoll INT,
Name VARCHAR(100),
Age INT,
Year INT,
Branch VARCHAR(100),
Section VARCHAR(20),
Department VARCHAR(100),
CGPA DOUBLE
);

Adjust the column definitions if your existing MySQL schema differs.

⚙️ Configuration

The application requires a JDBC connection similar to:

jdbc:mysql://localhost:3306/Students

Configure:

Host       → localhost
Port       → 3306
Database   → Students
Username   → your MySQL username
Password   → your MySQL password

Do not commit real database passwords to GitHub.

For a production-quality project, credentials should be loaded through environment variables or another secure configuration mechanism.

📦 Maven

The project uses Maven for dependency and build management.

Common Maven commands:

Compile
mvn compile
Run Tests
mvn test
Package
mvn package
Clean Build
mvn clean package
▶️ Running the Application
1. Clone the repository
   git clone <repository-url>
2. Open the project

Open the project in IntelliJ IDEA or another Java IDE.

3. Configure MySQL

Make sure:

MySQL Server is running
The Students database exists
The StudentDetails table exists
JDBC credentials are correctly configured
4. Build the project
   mvn clean package
5. Run the application

Run the application's Main class from your IDE or using the appropriate Maven configuration.

🧑‍💻 Development Notes

The project uses JDBC directly rather than an ORM such as Hibernate or JPA.

This makes the project particularly useful for understanding what happens underneath higher-level persistence frameworks.

The application explicitly works with:

Connection
↓
PreparedStatement / Statement
↓
executeQuery / executeUpdate
↓
ResultSet
↓
MySQL

This provides hands-on experience with the core JDBC workflow.

📚 What This Project Demonstrates

The project represents a practical implementation of the following flow:

Java
↓
Object-Oriented Programming
↓
Maven
↓
JDBC
↓
SQL
↓
MySQL

Rather than only learning SQL and JDBC theoretically, this project connects those concepts into a working application.

📈 Project Status

Status: Completed JDBC Practice Project

The primary goal of this project is to demonstrate practical Java database integration through a command-line Student Management System.

The application currently supports:

Database connection

Student creation

Student display

Student search

Student deletion

Student editing

Duplicate ID checking

Roll-number checking

Input validation

JDBC CRUD operations

MySQL persistence

Maven project structure

Git version control

👤 Author

Mirza Safwan Baig

Student / Developer

Interested in:

Java Backend Development
Data Science
Machine Learning
Database Systems
Software Development
📄 License

This project was created primarily for educational and learning purposes.

You may modify and extend the project for your own learning and development.