# 💰 Expense Manager — REST API

A secure and scalable personal expense management REST API built with Spring Boot.

---

## 🚀 Tech Stack

| Technology | Usage |
|------------|-------|
| Java 23 | Core Language |
| Spring Boot 4.x | Backend Framework |
| Spring Security | Authentication & Authorization |
| JWT | Stateless Token-based Auth |
| Spring Data JPA | ORM |
| MySQL | Database |
| Swagger / OpenAPI | API Documentation |
| Lombok | Boilerplate Reduction |
| Maven | Build Tool |

---

## ✨ Features

- ✅ JWT Authentication & Authorization
- ✅ User Registration & Login
- ✅ Secure — No data leak between users
- ✅ Category Management (CRUD)
- ✅ Transaction Management (CRUD)
- ✅ Pagination & Type-based Filtering
- ✅ Monthly Income/Expense Summary
- ✅ Category-wise Spending Analytics
- ✅ User Profile Management
- ✅ Global Exception Handling
- ✅ Swagger API Documentation

---

## 📁 Project Structure
src/main/java/com/expmang/
├── config/          # Security, JWT, Swagger config
├── controller/      # REST Controllers
├── dto/             # Data Transfer Objects
├── entity/          # JPA Entities
├── exception/       # Global Exception Handler
├── repository/      # Spring Data JPA Repositories
└── service/         # Business Logic
└── impl/

---

## ⚙️ Setup & Run

### Prerequisites
- Java 23
- MySQL 8.x
- Maven

### Steps

1. Clone the repository
```bash
   git clone https://github.com/yourusername/expense-manager.git
   cd expense-manager
```

2. Create MySQL database
```sql
   CREATE DATABASE expense_manager;
```

3. Create `.env` file in root folder
DB_URL=jdbc:mysql://localhost:3306/expense_manager
DB_USERNAME=your_mysql_username
DB_PASSWORD=your_mysql_password
JWT_SECRET=your_jwt_secret_key

4. Run the application
```bash
   ./mvnw spring-boot:run
```

5. Open Swagger UI
http://localhost:8080/swagger-ui.html

---

## �API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/auth/register` | Register new user |
| POST | `/auth/login` | Login & get JWT token |

### 👤 User
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/user/profile` | Get logged-in user profile |
| PUT | `/user/profile` | Update user name |

### 📂 Categories
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/categories` | Create new category |
| GET | `/categories` | Get all my categories |

### 💸 Transactions
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/transactions` | Add new transaction |
| GET | `/transactions/all` | Get all my transactions |
| PUT | `/transactions/{id}` | Update my transaction |
| DELETE | `/transactions/{id}` | Delete my transaction |
| GET | `/transactions?page=0&size=10` | Get transactions with pagination |
| GET | `/transactions/filter?type=EXPENSE` | Filter by type |
| GET | `/transactions/summary?month=6&year=2026` | Monthly summary |
| GET | `/transactions/analytics/category` | Category-wise spending |

---

## 🔒 Security

- Passwords hashed using **BCrypt**
- Stateless authentication using **JWT**
- Every user can only access their own data
- Sensitive config stored in `.env` file

---

## 📖 API Documentation

Full interactive API documentation available at:
http://localhost:8080/swagger-ui.html

