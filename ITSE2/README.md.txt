# Booktracker Application

This is a Java command-line application that connects to a SQLite database using JDBC.  
It allows users to manage and analyze reading habits data.

## Requirements
- Java 8 or higher
- SQLite JDBC Driver (`sqlite-jdbc-3.49.1.0.jar`)
- booktracker.db SQLite database file

## How to Run

1. Make sure the following files are in the same folder:
   - booktracker.db
   - sqlite-jdbc-3.49.1.0.jar
   - src/Main.java
   - src/BooktrackerApp.java

2. Compile the source code:javac -cp ".;sqlite-jdbc-3.49.1.0.jar" src/*.java

3. Run the application:java -cp ".;sqlite-jdbc-3.49.1.0.jar;src" Main

> Note: On Mac/Linux, replace ; with : in the classpath.

## Features Implemented

1. Add a new user to the database
2. Display all reading habit data for a specific user
3. Update the title of a book in the database
4. Delete a row from the ReadingHabit table
5. Calculate the mean age of users (using SQL)
6. Count users who have read pages from a specific book
7. Calculate the total number of pages read by all users
8. Find users who have read more than one book
9. Add a column name to the User table

## Database Schema

### Table: User
- userID (INTEGER, PRIMARY KEY)
- age (INTEGER)
- gender (TEXT)
- name (TEXT)

### Table: ReadingHabit
- habitID (INTEGER, PRIMARY KEY)
- userID (INTEGER, FOREIGN KEY → User.userID)
- pagesRead (INTEGER)
- book (TEXT)
- submissionMoment (DATETIME)

