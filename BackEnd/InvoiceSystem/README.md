# Invoice Pro

**Invoice Pro** is a Spring Boot–based invoicing system that allows you to manage customers, products, and invoices efficiently. It includes REST API endpoints for creating, reading, updating, and deleting invoice-related data, with MySQL as the database and OpenAPI/Swagger documentation for easy API exploration.

---

## 📌 Features
- **Customer Management** – Add, update, view, and delete customers.
- **Product Management** – Store product details with pricing.
- **Invoice Management** – Create invoices with linked customers and products.
- **Database Integration** – Persistent data storage with MySQL.
- **API Documentation** – Integrated Swagger UI via SpringDoc OpenAPI.
- **RESTful Endpoints** – Built using Spring Boot’s REST architecture.

---

## 🛠 Tech Stack
- **Java** – Core programming language.
- **Spring Boot 3.4.6** – Web, Data JPA, and core framework.
- **SpringDoc OpenAPI 2.8.7** – API documentation and Swagger UI.
- **MySQL 8.0.33** – Relational database.
- **Maven** – Dependency management and build tool.

---

## 📂 Project Structure
```
InvoiceSystem/
 ├── src/main/java/...   # Application source code
 ├── src/main/resources/ # Application properties, static resources
 ├── pom.xml             # Maven dependencies
 ├── .classpath          # Eclipse classpath config
 ├── .project            # Eclipse project config
```

---

## ⚙️ Installation & Setup

### 1️⃣ Prerequisites
- Java 17+ installed
- Maven installed
- MySQL database running

### 2️⃣ Database
Create a database in MySQL:
```sql
CREATE DATABASE invoice_db;
```

### 3️⃣ Configure the database
Edit `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/invoice_db
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 4️⃣ Build and run the application
```bash
mvn clean install
mvn spring-boot:run
```

---

## 📖 API Documentation
Once the application is running, access:
```
Swagger UI: http://localhost:9001/swagger-ui.html
API Docs : http://localhost:9001/v3/api-docs
```

---

## 🧪 Running Tests
```bash
mvn test
```

---

## 📜 License
This project is licensed under the MIT License – feel free to use and modify it.
