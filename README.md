# JDBC MySQL Connection Demo

Java Maven project that connects to a local MySQL database (`StudentsDB`) using JDBC and MySQL Connector/J.

## Setup
1. Install MySQL and create the `StudentsDB` database with a `students` table.
2. Replace the placeholder password (`password`) in `JDBCDemo.java` with your MySQL root password.
3. Run `JDBCDemo` from IntelliJ. It should print `Established Connection` followed by the student rows.