# ☕ Event Management System — Backend

<p align="center">
  <img src="https://img.shields.io/badge/Java-21-ED8B00?logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.6-6DB33F?logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/Spring%20Security-Authentication-6DB33F?logo=springsecurity&logoColor=white" alt="Spring Security" />
  <img src="https://img.shields.io/badge/PostgreSQL-Database-4169E1?logo=postgresql&logoColor=white" alt="PostgreSQL" />
  <img src="https://img.shields.io/badge/Maven-Build%20Tool-C71A36?logo=apachemaven&logoColor=white" alt="Maven" />
</p>

## 📌 About the Project

The Event Management System backend is a Java-based application built with Spring Boot. It provides server-side functionality for processing requests from the frontend, implementing business logic, applying security rules, and interacting with a PostgreSQL database.

**Backend repository:** [eventmanagement_backend](https://github.com/thejasree25/eventmanagement_backend)

## ✨ Main Responsibilities

- ☕ Process HTTP requests through Spring Boot.
- 🔄 Provide APIs for frontend integration.
- 🗄️ Store and retrieve application data using PostgreSQL.
- 🧩 Map Java entities to database tables using JPA and Hibernate.
- 🔐 Apply authentication and authorization rules using Spring Security.
- ✅ Validate incoming data where validation is implemented.
- 🔗 Integrate with configured OAuth providers.

## 🛠️ Technologies Used and Why

| Technology | Why I Used It |
|---|---|
| ☕ Java 21 | To implement the backend application using object-oriented programming. |
| 🌱 Spring Boot | To configure and run the Java backend with less manual setup. |
| 🌐 Spring Web | To build HTTP endpoints and REST APIs. |
| 🔐 Spring Security | To configure authentication and authorization. |
| 🗄️ Spring Data JPA | To simplify database access using repository interfaces. |
| 🔄 Hibernate | To map Java objects to relational database tables. |
| 🐘 PostgreSQL | To store structured application data reliably. |
| 📦 Maven | To manage dependencies, compile code, run tests, and package the application. |
| 🧪 Postman | To test API requests, request bodies, headers, and responses. |
| 🐙 Git and GitHub | To track source-code changes and share the project. |

### 💡 Why Spring Boot?

Spring Boot simplifies backend development through auto-configuration, starter dependencies, and an embedded server. This helps me build and run the application without configuring every component manually.

### 💡 Why Spring Data JPA?

Spring Data JPA reduces repetitive database-access code. Repository interfaces provide common operations such as saving, finding, updating, and deleting entities.

### 💡 Why PostgreSQL?

PostgreSQL is a relational database that supports structured data, relationships, constraints, and SQL queries. It is suitable for applications that need consistent and organized data storage.

### 💡 Why Spring Security?

Spring Security provides mechanisms for authentication and authorization. It helps protect endpoints and control access based on the application's configured security rules.

## 🔄 Backend Workflow

```text
🌐 React Frontend
        |
        | HTTP request
        v
🔐 Spring Security
        |
        v
🎮 Controller
        |
        v
⚙️ Business Logic
        |
        v
📚 Repository
        |
        v
🔄 Hibernate / JPA
        |
        v
🐘 PostgreSQL
        |
        v
📨 HTTP Response
        |
        v
🖥️ React Frontend
```

The exact sequence depends on the endpoint. For example, an endpoint that does not access the database may return a response without using a repository.

## 🏗️ Project Structure

```text
eventmanagement_backend/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── ...          # Application packages and Java classes
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/                # Tests, if present
│
├── .mvn/
├── mvnw
├── mvnw.cmd
├── pom.xml
├── .gitignore
└── README.md
```

## ⚙️ Installation and Setup

### Prerequisites

- JDK 21
- PostgreSQL
- Git
- Maven wrapper included in the project
- Postman or another API client

### 1. Clone the repository

```bash
git clone https://github.com/thejasree25/eventmanagement_backend.git
cd eventmanagement_backend
```

### 2. Create the database

Open PostgreSQL or pgAdmin and run:

```sql
CREATE DATABASE event_management;
```

### 3. Configure `application.properties`

File location:

```text
src/main/resources/application.properties
```

Example database configuration:

```properties
spring.application.name=eventmanagement
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/event_management
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

Set `DB_USERNAME` and `DB_PASSWORD` in your local environment or IDE run configuration.

Retain any other required application settings from the original configuration.

### 4. Configure OAuth if required

For Google and GitHub authentication, configure valid credentials locally:

```properties
spring.security.oauth2.client.registration.google.client-id=${GOOGLE_CLIENT_ID}
spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}

spring.security.oauth2.client.registration.github.client-id=${GITHUB_CLIENT_ID}
spring.security.oauth2.client.registration.github.client-secret=${GITHUB_CLIENT_SECRET}
```

Ensure the relevant OAuth2 client dependency and Spring Security configuration exist in the project.

Never commit real OAuth client secrets to GitHub.

### 5. Build the project

**Windows:**

```cmd
mvnw.cmd clean install
```

**macOS/Linux:**

```bash
./mvnw clean install
```

### 6. Run the application

**Windows:**

```cmd
mvnw.cmd spring-boot:run
```

**macOS/Linux:**

```bash
./mvnw spring-boot:run
```

The application is configured to run at:

```text
http://localhost:8080
```

Use the endpoints defined in the controller classes to access the actual API functionality.

## 🗄️ Database Workflow

1. The frontend sends a request to the backend.
2. The controller receives the request.
3. The application performs the required processing.
4. The repository accesses the database when necessary.
5. Hibernate and JPA handle entity persistence.
6. PostgreSQL stores or returns the requested data.
7. The backend sends the response to the frontend.

## 🧪 Testing APIs

Use Postman to test the endpoints implemented in the project.

For each endpoint, verify:

- HTTP method and URL
- Required request headers
- Request body, if applicable
- Authentication requirements
- Response body and status code
- Database changes, where applicable

Refer to the controller classes for the actual endpoints supported by the application.

## 🎤 Interview Explanation

**Q: Explain your Event Management System backend.**

I developed the backend using Java and Spring Boot. It handles HTTP requests from the React frontend, processes application logic, and interacts with PostgreSQL through Spring Data JPA and Hibernate. I use Spring Security to configure authentication and authorization. The frontend and backend communicate through HTTP APIs.

**Q: Why did you use Spring Boot?**

Spring Boot simplifies Java backend development by providing auto-configuration, starter dependencies, and an embedded server. It helps me create and run applications with less configuration.

**Q: What is the role of JPA and Hibernate?**

JPA defines the persistence API used to map Java objects to database records. Hibernate is an implementation of JPA that performs ORM operations. Spring Data JPA simplifies repository-based data access.

**Q: Why did you use PostgreSQL?**

I used PostgreSQL because the application requires a relational database for structured data and SQL-based operations.

**Q: What is the difference between authentication and authorization?**

Authentication verifies who a user is. Authorization determines what that authenticated user is allowed to access.

**Q: How does the frontend communicate with the backend?**

The React frontend sends HTTP requests to Spring Boot endpoints. The backend processes each request and returns an HTTP response, often containing JSON data.

## 🔒 Security Best Practices

- Store passwords, OAuth secrets, and API keys outside source control.
- Use environment variables or a secrets manager.
- Rotate credentials that have been exposed.
- Disable detailed stack traces and sensitive error messages in production.
- Configure suitable authorization rules for protected endpoints.
- Use HTTPS in production.
- Use database migrations and backups for production data.

## 🚀 Future Improvements

- Add automated unit and integration tests.
- Document API endpoints with OpenAPI/Swagger.
- Improve exception handling and validation.
- Add structured logging and monitoring.
- Configure production deployment and database migrations.

## 👩‍💻 Author

**Thejasree Ganginepalli**

- GitHub: [thejasree25](https://github.com/thejasree25)
- Backend repository: [eventmanagement_backend](https://github.com/thejasree25/eventmanagement_backend)

