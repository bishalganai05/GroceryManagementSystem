# Grocery Management System

## Overview

The Grocery Management System is a robust, monolithic Spring Boot application meticulously crafted to streamline and automate grocery-related operations. It offers a comprehensive suite of features, ranging from fundamental CRUD functionalities to advanced security measures, API documentation, and performance optimization through caching. Developed with a strong emphasis on best practices, clean code, and testability, this application serves as a solid and extensible foundation for a modern grocery management solution.

-----

## Key Features & Technologies

This project incorporates a wide array of functionalities and leverages cutting-edge technologies:

  * **Core Data Management**:

      * **CRUD Operations with ResponseEntity**: Implements standardized RESTful endpoints for Create, Read, Update, and Delete operations, ensuring consistent and informative HTTP responses using ResponseEntity.
      * **JPA Query Methods & @Query Annotation**: Efficient data retrieval is achieved using Spring Data JPA's derived query methods and custom JPQL/native queries, allowing for flexible and optimized database interactions.
      * **Transaction Management**: Ensures data integrity and consistency across multiple database operations through declarative transaction management.

  * **Robust Error Handling**:

      * **Centralized Error Handling with @ControllerAdvice**: Provides a unified and consistent approach to global exception handling, ensuring user-friendly and informative error responses across the entire API.

  * **API & Interoperability**:

      * **Content Negotiation**: Supports multiple data formats (e.g., JSON, XML) for API responses, enhancing client interoperability.
      * **Swagger/OpenAPI Documentation**: Interactive API documentation is automatically generated using SpringDoc OpenAPI Starter WebMVC UI, making it easy for developers to understand, test, and integrate with the API.

  * **Security & Authentication**:

      * **Comprehensive Spring Security Integration**: Implements a multi-layered security approach:
          * **Basic Authentication**: Provides initial secure access to the application.
          * **Custom Authentication & Password Encryption**: Tailored authentication logic with robust password encryption (e.g., BCrypt) for secure credential storage.
          * **JWT (JSON Web Tokens)**: Enables secure, stateless authentication for API calls, improving scalability and performance.
          * **CSRF Token Implementation**: Protects against Cross-Site Request Forgery attacks, enhancing application security.
          * **Authorization with Roles and Permissions**: Granular access control is enforced based on user roles and specific permissions, ensuring users only access authorized resources.
          * **Method Security**: Secures individual service and controller methods based on fine-grained authorization rules.
      * **User Management**: Fully featured module for managing user accounts, roles, and permissions within the system.

  * **Performance & Scalability**:

      * **Redis Cache**: Significantly enhances application performance by caching frequently accessed data, reducing database load and improving response times.
      * **CRON Scheduling**: Automates the execution of routine tasks at predefined intervals, optimizing background processes.

  * **Architectural & Development Enhancements**:

      * **Aspect-Oriented Programming (AOP)**: Utilizes AOP for implementing cross-cutting concerns (e.g., logging, performance monitoring) cleanly, separating them from core business logic.
      * **Spring Boot DevTools**: Provides a faster development cycle with features like automatic restarts and live reload.
      * **Lombok**: Reduces boilerplate code (getters, setters, constructors, etc.), making the codebase cleaner and more concise.

  * **Testing**:

      * **Unit Testing with JUnit & Mockito**: Comprehensive unit tests cover various components and business logic, ensuring code reliability and maintainability. This includes leveraging Test Lifecycle annotations and Java Reflection for advanced testing scenarios.

-----

## Technologies Used

The project is built upon a modern technology stack:

  * **Language**: Java 21
  * **Framework**: Spring Boot 3.5.3
      * spring-boot-starter-data-jpa (for ORM with Hibernate)
      * spring-boot-starter-web (for building RESTful APIs)
      * spring-boot-devtools (for development time productivity)
      * spring-boot-starter-test (for testing with JUnit, Mockito, Spring Test)
      * spring-boot-starter-security (for robust authentication and authorization)
      * spring-boot-starter-data-redis (for integrating with Redis cache)
  * **Database**: MySQL
      * mysql-connector-java
  * **API Documentation**: SpringDoc OpenAPI Starter WebMVC UI
  * **JSON Web Tokens**: JJWT (jjwt-api, jjwt-impl, jjwt-jackson)
  * **Utilities**: Lombok
  * **XML Processing (for Content Negotiation)**: Jackson Dataformat XML

-----

## Getting Started

Follow these instructions to set up and run the Grocery Management System on your local machine.

### Prerequisites

Ensure you have the following installed:

  * Java Development Kit (JDK) 21
  * Apache Maven 3.x
  * MySQL Database Server
  * Redis Server (Optional, but recommended for full functionality)

### Installation & Setup

1.  **Clone the Repository**:

    ```
    git clone https://github.com/bishalganai05/GroceryManagementSystem.git
    cd GroceryManagementSystem
    ```


2.  **Database Configuration**:

      * Create a new MySQL database (e.g., `grocery_db`).
      * Update the `src/main/resources/application.properties` (or `application.yml`) file with your MySQL database credentials and Redis connection details:

    <!-- end list -->

    ```properties
    # MySQL Database Configuration
    spring.datasource.url=jdbc:mysql://localhost:3306/grocery_db?useSSL=false&serverTimezone=UTC
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update # Recommended for development; use 'validate' or 'none' for production
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
    # Redis Cache Configuration
    spring.data.redis.host=localhost
    spring.data.redis.port=6379
    # spring.data.redis.password=your_redis_password # Uncomment if Redis requires a password
    ```

3.  **Build the Project**:

    ```
    mvn clean install
    ```

    This command compiles the source code, runs tests, and packages the application into a JAR file.

4.  **Run the Application**:

    ```
    mvn spring-boot:run
    ```

    The application will typically start on `http://localhost:8080`.

-----

## API Documentation (Swagger UI)

Once the application is running, you can access the interactive API documentation and test endpoints directly via Swagger UI:

http://localhost:8080/swagger-ui.html

This interface provides a clear overview of all available API endpoints, their expected parameters, and response structures.

<img width="1148" height="621" alt="Screenshot 2025-07-06 104518" src="https://github.com/user-attachments/assets/7c511c31-a015-4dd3-a597-79d09795b45e" />


-----

## Project Commits & Evolution

The project's development journey is transparently documented through its commit history, showcasing a progressive implementation of features and architectural enhancements:

  * Initial CRUD Operations with ResponseEntity
  * Refined Error Handling with ControllerAdvice
  * Content Negotiation and Exception Handling Integration
  * Implementation of Aspect-Oriented Programming (AOP)
  * Basic Authentication Setup
  * Spring Security Configuration Updates
  * CSRF Token Implementation for enhanced security
  * Custom Spring Authentication and Password Encryption
  * JWT (JSON Web Token) Integration for stateless security
  * Commencement of Unit Testing with JUnit and Mockito
  * Exploration of Test Lifecycle and Java Reflection in testing
  * Authentication Code Refinement
  * Spring Authorization with Roles and Permissions Implemented
  * Method Security for Fine-Grained Access Control
  * Comprehensive User Management Module
  * Redis Cache Integration for Performance Optimization
  * Swagger Configuration for API Documentation
  * CRON Scheduling for Automated Tasks
  * Advanced JPA: Query Methods and @Query Annotation
  * Robust Transaction Management

-----

## Future Roadmap

The strategic future planning for the Grocery Management System includes:

  * **Transition to Microservices Architecture**: Decoupling the current monolithic application into a set of independent, single-purpose microservices. This will enhance scalability, resilience, independent deployability, and allow for technology diversity across services, preparing the system for enterprise-level demands.

-----

## Contributing

To contribute to the Grocery Management System\! If you'd like to contribute, please follow these steps:

1.  Fork the repository.
2.  Create a new branch (`git checkout -b feature/YourFeatureName`).
3.  Make your changes and ensure tests pass.
4.  Commit your changes (`git commit -m 'Add new feature'`).
5.  Push to the branch (`git push origin feature/YourFeatureName`).
6.  Open a Pull Request.


Bishal

  * **GitHub**: [https://github.com/bishal-s](https://www.google.com/search?q=https://github.com/bishal-s) (Replace with actual if different)
  * **Email**: [your.email@example.com](mailto:your.email@example.com) (Optional: Add your professional email)

-----
