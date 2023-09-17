# Huge People Database

This application contains a database of people. It is a simple application that allows you to add, edit, and delete people from the database.
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