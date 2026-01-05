# Expense Tracker API

A Spring Boot REST API for tracking personal expenses with JWT authentication and MySQL database.

## Features

- **User Authentication**: Sign up and login with JWT (JSON Web Tokens).
- **Expense Management**: Create, read, update, and delete expenses.
- **Filtering**: Filter expenses by past week, month, 3 months, or custom date range.
- **Security**: Stateless session management using Spring Security.

## Tech Stack

- **Java 21**
- **Spring Boot 3** (Web, Data JPA, Security)
- **MySQL 8.0**
- **Docker & Docker Compose**
- **JWT (jjwt)**

## Getting Started

### Prerequisites

- Docker Desktop
- Java 21 (optional, if using Maven wrapper)

### Setup & Run

1. **Start the Database**
   ```bash
   docker-compose up -d
   ```
   This starts a MySQL container on port `3306`.

2. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```
   The API will be available at `http://localhost:8080`.

## API Endpoints

### Authentication

| Method | Endpoint | Description | Body |
|--------|----------|-------------|------|
| `POST` | `/api/auth/signup` | Register a new user | `{ "username": "...", "password": "..." }` |
| `POST` | `/api/auth/signin` | Login & get JWT | `{ "username": "...", "password": "..." }` |

### Expenses

*All expense endpoints require `Authorization: Bearer <token>` header.*

| Method | Endpoint | Description | Params / Body |
|--------|----------|-------------|---------------|
| `POST` | `/api/expenses` | Add an expense | `{ "title": "...", "amount": 10.0, "category": "FOOD", "date": "2023-01-01" }` |
| `GET` | `/api/expenses` | List all expenses | Query params: `filter` (week, month, 3months, custom), `startDate`, `endDate` |
| `GET` | `/api/expenses/{id}` | Get expense details | - |
| `PUT` | `/api/expenses/{id}` | Update an expense | Same as POST body |
| `DELETE` | `/api/expenses/{id}` | Delete an expense | - |

## Configuration

- **Database**: configured in `src/main/resources/application.properties`
- **JWT Secret**: configured in `application.properties` (change for production)

## Testing

Run unit and integration tests (uses in-memory H2 database):

```bash
./mvnw test
```
