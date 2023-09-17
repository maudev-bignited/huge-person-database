# Huge People Database

This application contains a database of people. It is a simple application that allows you to add and delete people from the database.
It also contains some extra functions, e.g. to calculate the average age of all people in the database.
The application contains some bugs that you need to find, and eventually fix.

## Installation

- You will need Java SDK 17, which you can download here: https://www.oracle.com/java/technologies/downloads/#java17
- You need to enable Lombok annotation processing in your IDE. See here for instructions: https://www.baeldung.com/lombok-ide

All you need to do is run the HugePeopleDatabaseApplication class.

## Usage

The application will start on port 8080. You can access the application at http://localhost:8080.
You can interact with the application using the Swagger UI. You can access the Swagger UI at http://localhost:8080/swagger-ui.html.

## Database

The application uses an in-memory H2 database. The database is initialized with some data when the application starts.
The database is also reachable at http://localhost:8080/h2-console. The JDBC URL is `jdbc:h2:mem:peopledb`. The username is `sa` and the password is empty.

## Logging bugs

You can log bugs in the issues section of the repository on GitHub. Please use the following template.
Also make sure not to log a bug that someone else has already logged!

1. a quick summary that describes the issue
2. a step-by-step instructional list of how to reproduce the issue
3. the wrong outcome that the issue results in
4. the expected correct outcome
