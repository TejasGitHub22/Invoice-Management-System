# Invoice Management System

Full-stack **Invoice Management System** with a **Spring Boot REST API**, **MySQL** persistence, **Swagger/OpenAPI** docs, and a **Bootstrap + JavaScript** web frontend for customers, products, invoices, and payments.

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.6-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8-4479A1?style=flat-square&logo=mysql&logoColor=white)
![License](https://img.shields.io/badge/License-MIT-green?style=flat-square)

**Repository:** [github.com/TejasGitHub22/Invoice-Management-System](https://github.com/TejasGitHub22/Invoice-Management-System)

---

## Highlights for reviewers

| Area | Details |
|------|---------|
| **Architecture** | Layered backend: Controller → Service → JPA Repository |
| **API design** | REST endpoints for CRUD on customers & products; invoice creation & payments |
| **Documentation** | SpringDoc OpenAPI — interactive Swagger UI |
| **Frontend** | Multi-page HTML app using Fetch API against the backend |
| **Data model** | Customers, products, invoice line items, payments with GST calculations |

---

## Features

### Customer management
- Create and list customers (name, gender, DOB, email, mobile, city)
- REST: `GET/POST/PUT/DELETE /api/customer`

### Product management
- Manage catalog with rate, GST %, and stock
- REST: `GET/POST/PUT/DELETE /api/product`

### Invoice management
- Create invoices with multiple line items and auto GST totals
- View all invoices with paid / remaining amounts and status
- Record payments against pending invoices
- View invoice detail and payment history

### API documentation
- Swagger UI: `http://localhost:9001/swagger-ui.html`
- OpenAPI JSON: `http://localhost:9001/v3/api-docs`

---

## Tech stack

| Layer | Technologies |
|-------|----------------|
| Backend | Java 17+, Spring Boot 3.4.6, Spring Data JPA, Spring Web |
| Database | MySQL 8 |
| API docs | SpringDoc OpenAPI 2.8.7 |
| Frontend | HTML5, CSS3, Bootstrap 5, JavaScript (Fetch API), jQuery |
| Build | Maven |

---

## Project structure

```
Invoice-Management-System/
├── BackEnd/
│   └── InvoiceSystem/          # Spring Boot application
│       ├── src/main/java/mypackage/
│       │   ├── App.java
│       │   ├── controller/     # REST controllers
│       │   ├── services/       # Business logic
│       │   ├── repository/     # JPA repositories
│       │   └── model/          # Entities & DTOs
│       └── src/main/resources/
│           └── application.properties.example
├── FrontEnd/
│   └── InvoiceSystem/          # Static web UI
│       ├── Customer.html
│       ├── Product.html
│       ├── NewInvoice.html
│       └── AllInvoices.html
└── README.md
```

---

## Prerequisites

- **JDK 17+**
- **Maven 3.6+**
- **MySQL 8** (or MariaDB)
- Modern web browser

---

## Quick start

### 1. Clone the repository

```bash
git clone https://github.com/TejasGitHub22/Invoice-Management-System.git
cd Invoice-Management-System
```

### 2. Create the database

```sql
CREATE DATABASE invoicesystem;
```

Tables are created automatically via Hibernate (`ddl-auto=update`).

### 3. Configure the backend

```bash
cd BackEnd/InvoiceSystem
copy src\main\resources\application.properties.example src\main\resources\application.properties
```

Edit `application.properties` with your MySQL username and password.

> `application.properties` is **gitignored** so credentials are never pushed to GitHub.

### 4. Run the backend

```bash
mvn clean install
mvn spring-boot:run
```

Backend runs at **http://localhost:9001**

### 5. Open the frontend

Open these files in your browser (or use Live Server in VS Code):

- `FrontEnd/InvoiceSystem/Customer.html`
- `FrontEnd/InvoiceSystem/Product.html`
- `FrontEnd/InvoiceSystem/NewInvoice.html`
- `FrontEnd/InvoiceSystem/AllInvoices.html`

The UI calls `http://localhost:9001/api/...`.

---

## API endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/customer` | List customers |
| POST | `/api/customer` | Add customer |
| GET | `/api/customer/{id}` | Get customer |
| PUT | `/api/customer/{id}` | Update customer |
| DELETE | `/api/customer/{id}` | Delete customer |
| GET | `/api/product` | List products |
| POST | `/api/product` | Add product |
| GET | `/api/product/{id}` | Get product |
| PUT | `/api/product/{id}` | Update product |
| DELETE | `/api/product/{id}` | Delete product |
| GET | `/api/allinvoices` | List all invoices |
| POST | `/api/newinvoice` | Create invoice |
| GET | `/api/invoice/{id}` | Invoice details |
| POST | `/api/addpayment` | Add payment |
| GET | `/api/getinvoicepayments/{id}` | Payment history |

---

## Screenshots

Add images under `docs/screenshots/` and link them here before sharing on your resume:

- Login / dashboard (optional)
- Customer list
- New invoice flow
- All invoices with payment modal

---

## Detailed docs

- [Backend README](BackEnd/InvoiceSystem/README.md)
- [Frontend README](FrontEnd/InvoiceSystem/README_FRONTEND.md)

---

## Roadmap

- [ ] Environment-based API base URL in frontend (no hardcoded `localhost`)
- [ ] Spring Security + JWT authentication
- [ ] Single-page app (React) or serve frontend from Spring Boot `static/`
- [ ] Unit & integration tests
- [ ] Docker Compose for MySQL + app

---

## License

MIT License — see [LICENSE](LICENSE).
