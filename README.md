# Expense Tracker

A comprehensive Spring Boot application for managing personal and business expenses efficiently. This project provides a robust REST API for tracking, categorizing, and analyzing expenses with secure user authentication.

---

## 📋 Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Spring Boot Modules](#spring-boot-modules)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Usage](#usage)
- [Project Structure](#project-structure)
- [API Endpoints](#api-endpoints)
- [Contributing](#contributing)
- [License](#license)
- [Roadmap](#roadmap)
- [Acknowledgments](#acknowledgments)

---

## ✨ Features

- 🔐 **Secure Authentication**: JWT-based authentication with Spring Security
- 💰 **Expense Management**: Create, read, update, and delete expenses
- 📊 **Expense Analytics**: Track spending patterns and generate reports
- 🏷️ **Categorization**: Organize expenses by custom categories
- 👤 **User Management**: Multi-user support with isolated data
- 🔍 **Advanced Filtering**: Filter expenses by date, category, and amount
- 🌐 **RESTful API**: Clean and intuitive REST API endpoints
- 💾 **Data Persistence**: PostgreSQL database for reliable data storage
- ✅ **Testing**: Comprehensive test coverage for reliability

---

## 🛠️ Technology Stack

- **Language**: Java 21
- **Framework**: Spring Boot 4.0.0
- **Build Tool**: Maven
- **Database**: PostgreSQL
- **Authentication**: JWT (JSON Web Tokens)
- **ORM**: JPA/Hibernate
- **Additional Libraries**: Lombok, JJWT

---

## 🚀 Spring Boot Modules

This project utilizes the following Spring Boot starter modules:

### Core Modules

| Module | Purpose | Version |
|--------|---------|---------|
| **spring-boot-starter-webmvc** | REST API development and web request handling | 4.0.0 |
| **spring-boot-starter-security** | Authentication and authorization framework | 4.0.0 |
| **spring-boot-starter-data-jpa** | Object-Relational Mapping (ORM) and database interaction | 4.0.0 |

### Additional Dependencies

| Dependency | Purpose | Version |
|-----------|---------|---------|
| **JJWT (JSON Web Token Library)** | JWT token creation and validation | 0.11.5 |
| **jjwt-api** | JWT API interface | 0.11.5 |
| **jjwt-impl** | JWT implementation | 0.11.5 |
| **jjwt-jackson** | JSON serialization for JWT | 0.11.5 |
| **Lombok** | Reduce boilerplate code with annotations | Latest |
| **PostgreSQL Driver** | Database connectivity | Latest |

### Testing Modules

| Module | Purpose |
|--------|---------|
| **spring-boot-starter-webmvc-test** | Web layer testing utilities |
| **spring-boot-starter-data-jpa-test** | JPA and database testing |

### Module Descriptions

#### 1. **Spring Boot Starter WebMVC**
Provides the foundation for building REST APIs with Spring MVC. Enables:
- HTTP request handling
- Controller annotations (@GetMapping, @PostMapping, etc.)
- Request/response mapping
- Content negotiation

#### 2. **Spring Boot Starter Security**
Implements security features including:
- Authentication mechanisms
- Authorization filters
- CSRF protection
- User credential management
- Integration with JWT for token-based authentication

#### 3. **Spring Boot Starter Data JPA**
Facilitates database operations with:
- Entity mapping with `@Entity` annotations
- Repository pattern implementation
- Automatic CRUD operations
- Query methods
- Transaction management

---

## 📋 Prerequisites

Before running this application, ensure you have:

- **Java Development Kit (JDK)**: Version 21 or higher
- **Maven**: Version 3.6.0 or higher
- **PostgreSQL**: Version 12 or higher
- **Git**: For version control

---

## 🔧 Installation

### Step 1: Clone the Repository

```bash
git clone https://github.com/Akshit75123/expense-tracker.git
cd expense-tracker
```

### Step 2: Configure Database

Create a PostgreSQL database and configure the connection:

```properties
# application.properties or application.yml

spring.datasource.url=jdbc:postgresql://localhost:5432/expense_tracker
spring.datasource.username=your_db_username
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### Step 3: Build the Project

```bash
mvn clean install
```

### Step 4: Run the Application

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`

---

## ⚙️ Configuration

### JWT Configuration

Configure JWT secret and expiration in your application properties:

```properties
jwt.secret=your_secret_key_here
jwt.expiration=86400000
```

### Database Configuration

Ensure PostgreSQL is running and accessible with the credentials specified in `application.properties`.

---

## 📖 Usage

### Starting the Application

```bash
mvn spring-boot:run
```

### Accessing the API

The REST API is available at:
```
http://localhost:8080/api
```

### Authentication

1. Register a new user
2. Obtain JWT token via login
3. Include token in Authorization header for subsequent requests

```
Authorization: Bearer <your_jwt_token>
```

---

## 📁 Project Structure

```
expense-tracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/expense/
│   │   │       ├── controller/          # REST API endpoints
│   │   │       ├── service/             # Business logic
│   │   │       ├── repository/          # Data access layer
│   │   │       ├── entity/              # JPA entities
│   │   │       ├── dto/                 # Data transfer objects
│   │   │       ├── security/            # Security configuration
│   │   │       └── ExpenseTrackerApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application.yml
│   └── test/
│       └── java/
│           └── com/expense/
├── pom.xml                              # Maven configuration
└── README.md
```

---

## 🔌 API Endpoints

### User Management
- `POST /api/auth/register` - Register new user
- `POST /api/auth/login` - Login user
- `GET /api/users/profile` - Get user profile

### Expense Management
- `GET /api/expenses` - Get all expenses
- `POST /api/expenses` - Create new expense
- `GET /api/expenses/{id}` - Get expense by ID
- `PUT /api/expenses/{id}` - Update expense
- `DELETE /api/expenses/{id}` - Delete expense

### Reports
- `GET /api/reports/summary` - Get expense summary
- `GET /api/reports/by-category` - Get expenses by category

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

## 📄 License

This project is licensed under the MIT License. See the LICENSE file for details.

---

## 🗓️ Roadmap

- [ ] Mobile app integration
- [ ] Advanced reporting with charts
- [ ] Expense splitting feature
- [ ] Budget tracking and alerts
- [ ] Multi-currency support
- [ ] Recurring expense automation
- [ ] Cloud backup integration
- [ ] Export to PDF/CSV functionality

---

## 🙏 Acknowledgments

- Spring Boot team for the excellent framework
- PostgreSQL community
- JWT library contributors
- All contributors and users of this project

---

**Happy tracking! 💸**
