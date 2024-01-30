# Department Spring Boot Project

This Spring Boot project is developed using Eclipse STS and provides functionality for managing departments. It offers RESTful APIs for CRUD operations on departments and supports RabbitMQ for asynchronous message processing.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
  - [Import Project](#import-project)
  - [Run the Application](#run-the-application)
  - [Swagger API Documentation](#swagger-api-documentation)
- [Configuration](#configuration)
- [RabbitMQ Integration](#rabbitmq-integration)
- [API Endpoints](#api-endpoints)
- [Security](#security)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

## Prerequisites

Before working with the project in Eclipse STS, ensure you have the following installed:

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

1. Right-click on the project.
2. Run As -> Spring Boot App.

### Swagger API Documentation

Once the application is running, you can access Swagger API documentation at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Configuration

The project uses `application.properties` for configuration. Customize the properties as needed for your environment.

## RabbitMQ Integration

If you want to enable RabbitMQ integration for asynchronous message processing, configure the RabbitMQ properties in the `application.properties` file.

## API Endpoints

- **GET /department:** Get all departments.
- **GET /department/{id}:** Get a department by ID.
- **POST /department:** Create a new department.
- **PUT /department/{id}:** Update a department by ID.
- **DELETE /department/{id}:** Delete a department by ID.

For RabbitMQ integration:
- **POST /department/rabbitmq/save:** Save a new department with RabbitMQ.
- **PUT /department/{id}/rabbitmq/update:** Update a department by ID with RabbitMQ.

## Security

The project uses Spring Security for authentication and authorization. Adjust the security configurations in the `SecurityConfig` class.

## Testing

Unit and integration tests are available in the `src/test` directory. Run tests using:

```bash
mvn test
