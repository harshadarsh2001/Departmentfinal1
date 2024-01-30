# Department Spring Boot Project

This Spring Boot project is designed for managing department data through RESTful APIs. It supports CRUD operations on departments and includes RabbitMQ integration for asynchronous message processing.

## Project Structure

- `src/main/java/com/harshproject`: Main application code.
  - `controller`: REST controllers handling HTTP requests.
  - `dto`: Data Transfer Objects for data exchange.
  - `entity`: JPA entities representing database tables.
  - `filter`: Custom filters for request processing.
  - `service`: Business logic and services.
  - `config`: Configuration classes for various components.
  - `rabbitmq`: Messaging Support.
- `src/test/java`: Unit and integration tests.

## Prerequisites

Ensure you have the following installed before working with the project:

- [Eclipse STS](https://spring.io/tools) (Spring Tool Suite)
- Java Development Kit (JDK)
- Maven
- RabbitMQ (if using RabbitMQ for messaging)

## Getting Started

### Import Project

1. Open Eclipse STS.
2. File -> Import -> Existing Maven Project.
3. Browse to the project directory and select the `pom.xml` file.
4. Click Finish.

### Run the Application

To run the project:

1. Right-click on `DepartmentSpringbootApplication` class.
2. Run As -> Spring Boot App.

### Swagger API Documentation

Access Swagger API documentation once the application is running:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Configuration

The project uses `application.properties` for configuration. Customize the properties as needed for your environment.

## Security

Security is implemented using Spring Security and JWT (JSON Web Tokens) for user authentication and authorization.

- **Security Configuration:** See `SecurityConfig` class.
- **User Authentication:** Implemented in `AuthController` using `AuthenticationManager`.
- **JWT Token Generation:** Handled by `JwtService` and verified by `JwtAuthFilter`.
- **User Details:** `UserInfoUserDetailsService` loads user details.

## RabbitMQ Integration

RabbitMQ integration is included for asynchronous message processing.

- **RabbitMQ Configuration:** Check `RabbitMQConfig` class.
- **Sending Messages:** Implemented in `DepartmentController` for save and update operations.

## API Endpoints

### Departments

- **GET /department:** Get all departments.
- **GET /department/{id}:** Get a department by ID.
- **POST /department:** Create a new department.
- **PUT /department/{id}:** Update a department by ID.
- **DELETE /department/{id}:** Delete a department by ID.

### Email Notifications

The project includes scheduled tasks that send email notifications for various activities. Email notifications are sent for tasks executed every 10 seconds, 5 minutes, 30 minutes, and 1 hour.

### Custom Health Indicator

A custom health indicator is implemented to check the health of the database connection. It provides insights into the status of the database and is accessible at `/actuator/health`.

### Aspect-Oriented Programming (AOP) for Email Notifications

Aspect-Oriented Programming is used to send email notifications based on method execution. Emails are sent for successful and error scenarios, providing insights into the execution flow.

### Utilizing Spring Profiles

Spring profiles are used for managing different environments. The application is configured with profiles such as `dev`, `test`, `rabbitmq` and `prod`. Adjust the `application.properties` file accordingly.

### RabbitMQ Integration

- **POST /department/rabbitmq/save:** Save a new department with RabbitMQ.
- **PUT /department/{id}/rabbitmq/update:** Update a department by ID with RabbitMQ.

## Health Check

- **GET /actuator/health:** Health check endpoint.

## Testing

Unit and integration tests are available in the `src/test` directory. Run tests using:

```bash
mvn test
