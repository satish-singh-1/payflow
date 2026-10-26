# PayFlow

### Distributed Payment Ledger & Reconciliation System

PayFlow is a backend payment platform built with Java and Spring Boot, designed around secure authentication, reliable financial transactions, ledger integrity, and payment reconciliation.

The system follows a modular, feature-based architecture with an emphasis on security, maintainability, data consistency, and scalable backend engineering.

---

## Overview

Payment platforms must handle sensitive information, maintain accurate financial records, prevent duplicate operations, and preserve consistency across transaction workflows.

PayFlow is designed to address these challenges through a structured backend architecture, secure API design, relational data persistence, and reliable transaction-processing principles.

## Features

- **User Management** — User registration and account management.
- **Authentication & Authorization** — JWT-based authentication and access control.
- **Password Security** — BCrypt password hashing.
- **RESTful APIs** — Versioned endpoints and request validation.
- **Database Management** — MySQL persistence using Spring Data JPA and Hibernate.
- **Database Migrations** — Version-controlled schema changes using Flyway.
- **Wallet Management** — Wallet lifecycle and balance operations.
- **Financial Ledger** — Consistent financial records using double-entry accounting principles.
- **Transaction Processing** — Transaction lifecycle management and duplicate-request protection.
- **Payment Processing** — Structured payment workflows and transaction state management.
- **Reconciliation** — Comparison of payment records and ledger entries.
- **Caching & Asynchronous Processing** — Redis and Kafka integration.
- **Observability** — Application health, logging, and operational monitoring.

## Technology Stack

| Category | Technology |
|---|---|
| Language | Java 21 |
| Backend Framework | Spring Boot |
| Security | Spring Security, JWT |
| ORM | Spring Data JPA, Hibernate |
| Database | MySQL |
| Schema Migrations | Flyway |
| Build Tool | Maven |
| Validation | Jakarta Bean Validation |
| Caching | Redis |
| Messaging | Apache Kafka |
| Version Control | Git, GitHub |
| API Style | REST |

## System Architecture

PayFlow uses a feature-based package architecture. Each business feature groups its own controllers, services, repositories, entities, and DTOs, while shared infrastructure remains separate.

```text
com.payflow
├── auth/
│   ├── controller/
│   ├── service/
│   └── dto/
├── user/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── wallet/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── transaction/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── payment/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── reconciliation/
│   ├── controller/
│   ├── service/
│   ├── repository/
│   ├── entity/
│   └── dto/
├── security/
│   └── jwt/
├── config/
├── common/
│   ├── exception/
│   └── response/
└── PayflowApplication.java
```

## Core Design Principles

### Security
- Store passwords using secure password hashing.
- Keep credentials and signing keys outside source control.
- Validate JWT signatures and expiration before allowing protected access.
- Enforce authorization at appropriate API boundaries.

### Financial Integrity
- Represent monetary values using decimal-safe types.
- Record financial movements with a consistent ledger model.
- Use database transactions for atomic financial operations.
- Enforce idempotency to prevent duplicate processing.
- Preserve auditable transaction histories.

### Reliability
- Validate incoming requests.
- Apply consistent error handling.
- Handle transaction failures without leaving inconsistent financial records.
- Design asynchronous workflows with retry and failure-handling strategies.

## API Overview

Base path: `/api/v1`

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/auth/register` | Register a user |
| `POST` | `/auth/login` | Authenticate a user and generate a JWT |
| `GET` | `/users/me` | Retrieve the authenticated user's profile |
| `GET` | `/wallets` | Retrieve wallets |
| `POST` | `/transactions` | Initiate a transaction |
| `GET` | `/transactions/{id}` | Retrieve transaction details |
| `POST` | `/payments` | Initiate a payment |
| `GET` | `/payments/{id}` | Retrieve payment details |
| `POST` | `/reconciliation/runs` | Start a reconciliation run |

The endpoint list describes the intended API contract. Only endpoints implemented and verified in the codebase should be treated as currently available.

### Authentication Example

Request:

```http
POST /api/v1/auth/login
Content-Type: application/json
```

```json
{
  "email": "user@example.com",
  "password": "your-password"
}
```

Example response:

```json
{
  "accessToken": "your-jwt-access-token",
  "tokenType": "Bearer",
  "expiresIn": 900000
}
```

Use the token as a Bearer token when calling protected endpoints after JWT request validation is implemented.

## Getting Started

### Prerequisites

- Java 21
- Maven
- MySQL
- Git

### 1. Clone the repository

```bash
git clone https://github.com/YOUR_USERNAME/payflow.git
cd payflow
```

Replace `YOUR_USERNAME` with your GitHub username.

### 2. Create the database

```sql
CREATE DATABASE payflow_db;
```

### 3. Configure environment variables

Set the following variables in your operating system or IDE run configuration:

```text
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_base64_encoded_secret
```

Generate a cryptographically secure Base64-encoded JWT secret containing at least 32 random bytes. Never commit real credentials or secret values.

### 4. Configure application properties

In `src/main/resources/application.properties`, configure the relevant settings:

```properties
spring.application.name=payflow

spring.datasource.url=jdbc:mysql://localhost:3306/payflow_db
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=validate
spring.jpa.open-in-view=false

spring.flyway.enabled=true

jwt.secret=${JWT_SECRET}
jwt.expiration-ms=900000
```

Preserve any additional settings required by your existing application.

### 5. Run the application

```bash
mvn spring-boot:run
```

Flyway applies database migrations during startup, and Hibernate validates the database schema according to the configured JPA settings.

## Development Roadmap

- [x] Project setup and database connectivity
- [x] Initial Flyway schema migration
- [x] User registration foundation
- [x] Login endpoint and JWT generation foundation
- [ ] JWT request validation and protected endpoints
- [ ] Role-based authorization
- [ ] Standardized exception handling
- [ ] Wallet management
- [ ] Double-entry ledger
- [ ] Idempotent transaction processing
- [ ] Payment lifecycle management
- [ ] Reconciliation workflows
- [ ] Redis integration
- [ ] Kafka-based asynchronous processing
- [ ] Automated build and quality checks
- [ ] Deployment and monitoring

## Engineering Principles

- **Modularity:** Separate business features and infrastructure concerns.
- **Security:** Protect credentials, validate input, and enforce authorization.
- **Consistency:** Maintain reliable financial records and atomic operations.
- **Idempotency:** Prevent duplicate execution of financial requests.
- **Auditability:** Preserve transaction history for investigation and reconciliation.
- **Maintainability:** Use clear naming, focused responsibilities, and consistent conventions.
- **Observability:** Make failures and operational behavior easier to diagnose.

## Project Status

PayFlow is under active development. The repository documents the overall system design while implementation progresses feature by feature. The available API surface and completed functionality should be verified against the source code.

## License

No license has been selected. All rights are reserved by default unless a license is added to the repository.

---

**PayFlow** — Engineering reliable financial systems with Java and Spring Boot.
