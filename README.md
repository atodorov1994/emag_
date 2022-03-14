# EMAG

This is a simple web application , based on Spring.


## Project structure

The project structure follows the MVC pattern:
- `controller` package in `src/main/java/com.emag` contains all the controllers for all the endpoints of the application.
- `model` package in `src/main/java/com.emag` contains all the entities , dtos , daos and repositories .
- `service` package in `src/main/java/com.emag` handles the back-end business logic.
- `util` package in `src/main/java/com.emag` helper classes with some validations , for more readability in the services.
- `config` package in `src/main/java/com.emag` contains some configuration files.
- `exception` package in `src/main/java/com.emag` contains some custom made exceptions , exception handlers etc.


## Technologies used

- Java 11 
- Spring Framework
- Spring Boot + Tomcat Servlet Container
- Spring Data : JPA + Hibernate ; JdbcTemplate
- Spring MVC: RESTful Webservice
- Lombok
- ModelMapper
- Bean Validation API
- Passay - for generating random password
- IntelliJ Maven, MySQL, Postman, Git
