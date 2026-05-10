# Spring WebFlux Demo Application

A reactive web application built with Spring Boot WebFlux demonstrating Server-Sent Events (SSE) streaming and reactive programming concepts.

## Overview

This is a Spring Boot application that showcases reactive programming using **Spring WebFlux**. It provides REST endpoints that stream user data in real-time using Server-Sent Events (SSE) with timestamp information for each record.

## Key Features

- **Reactive Programming**: Built on Spring WebFlux for non-blocking, asynchronous operations
- **Server-Sent Events (SSE)**: Stream user data in real-time to clients
- **Timestamp Tracking**: Each user record includes timestamp in `HH:mm:ss` format
- **RESTful API**: Clean and well-documented API endpoints with OpenAPI/Swagger documentation
- **Comprehensive Testing**: JUnit 5 tests using WebTestClient for reactive testing

## Project Structure

```
application/
├── src/main/java/com/springflux/demo/
│   ├── DemoApplication.java           # Main Spring Boot application
│   ├── config/
│   │   └── OpenApiConfig.java         # OpenAPI/Swagger configuration
│   ├── controller/
│   │   ├── ReactiveUserController.java # REST controller with reactive endpoints
│   │   └── UserRouter.java            # Functional routing configuration
│   ├── model/
│   │   └── User.java                  # User entity with timestamp
│   └── service/
│       └── UserHandler.java           # Business logic handler
├── src/test/java/com/springflux/demo/
│   ├── controller/
│   │   └── ReactiveUserControllerTest.java  # JUnit 5 tests
│   └── DemoApplicationTests.java      # Application integration tests
├── src/main/resources/
│   └── application.yaml               # Application configuration
└── pom.xml                            # Maven dependencies
```

## API Endpoints

### 1. Get User by ID
**GET** `/api/user/{id}`

Returns a single user with current timestamp.

**Response:**
```json
{
  "id": "1",
  "name": "Ayush",
  "timestamp": "14:30:45"
}
```

### 2. Get All Users (Stream)
**GET** `/api/users`

Returns a Flux stream of 3 users with Server-Sent Events format.

**Content-Type:** `text/event-stream`

### 3. Stream Users with Delay
**GET** `/api/streamUsers`

Streams all 10 users with a 2-second delay between each record, including real-time timestamps.

**Content-Type:** `text/event-stream`

**Endpoint Details:**
- Returns 10 pre-defined users
- Each record includes a real-time timestamp in `HH:mm:ss` format
- 2-second delay between each streamed record
- Perfect for demonstrating reactive streaming and SSE

## Technology Stack

- **Java 17**
- **Spring Boot 4.0.6**
- **Spring WebFlux** - Reactive web framework
- **Project Reactor** - Reactive streams implementation
- **Lombok** - Code generation for boilerplate reduction
- **SpringDoc OpenAPI** - API documentation and Swagger UI
- **JUnit 5** - Testing framework
- **Mockito** - Mocking framework

## Running the Application

### Prerequisites
- Java 17 or higher
- Maven 3.6+

### Build and Run

```bash
# Navigate to project directory
cd demo

# Build the project
./mvnw clean install

# Run the application
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`

### Access API Documentation

Once the application is running, visit:
- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON**: `http://localhost:8080/v3/api-docs`

## Dependencies

### Main Dependencies
- spring-boot-starter-webflux
- spring-boot-starter-actuator
- springdoc-openapi-starter-webmvc-ui

### Test Dependencies
- spring-boot-starter-test
- reactor-test
- junit-jupiter

## Development Notes

- The `DateTimeFormatter` is configured at the controller level to ensure consistent timestamp formatting across all endpoints
- Reactive `Flux` and `Mono` types from Project Reactor provide non-blocking streams
- The 2-second delay in `/streamUsers` endpoint is achieved using `delayElements(Duration.ofSeconds(2))`
- WebTestClient is used in tests to assert on reactive streams

