Project Description:
A desktop-based application built using Java Swing and MySQL to efficiently manage employee records.
The system supports CRUD operations such as adding, viewing, updating, searching, and removing employees through a user-friendly interface.

How to Run:

1.Clone this repository:

git clone https://github.com/polepallikeerthi/Employee-Management-System.git

2.Import the project into Eclipse/IntelliJ.

3.Database Setup

Open MySQL and run the following:
CREATE DATABASE employeemanagement;
USE employeemanagement;

CREATE TABLE login (
username VARCHAR(20),
password VARCHAR(20)
);

INSERT INTO login VALUES ('keerthi', '123456789');

CREATE TABLE employee (
name VARCHAR(40),
fname VARCHAR(40),
dob VARCHAR(40),
salary VARCHAR(40),
address VARCHAR(40),
phone VARCHAR(40),
email VARCHAR(40),
education VARCHAR(40),
designation VARCHAR(40),
aadhar VARCHAR(40),
empID VARCHAR(40) PRIMARY KEY
);

4.Make sure your MySQL server is running and the login table contains valid username/password records (e.g., ('keerthi', '123456789'))
Ensure MySQL is running and database is set up.

5.Run Splash.java to start the application.
