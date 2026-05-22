# Invoice Management System

Full-stack **Invoice Management System** with a **Spring Boot REST API**, **MySQL** persistence, **Swagger/OpenAPI** docs, and a **React (Vite)** SPA for customers, products, invoices, and payments.

![Java](https://img.shields.io/badge/Java-17+-orange?style=flat-square)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.6-6DB33F?style=flat-square&logo=springboot&logoColor=white)
![React](https://img.shields.io/badge/React-19-61DAFB?style=flat-square&logo=react&logoColor=black)
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
| **Frontend** | React SPA with React Router, Axios, and a modern dashboard UI |
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
| Frontend | React 19, Vite, React Router, Axios |
| Build | Maven (backend), npm (frontend) |

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
│   ├── invoice-ui/             # React SPA (primary UI)
│   │   └── src/pages/          # Dashboard, Customers, Products, Invoices
│   └── InvoiceSystem/          # Legacy HTML UI (optional)
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

### 5. Run the React frontend

```bash
cd FrontEnd/invoice-ui
npm install
cp .env.example .env
npm run dev
```

Open **http://localhost:5173**

The UI calls the backend at `http://localhost:9001` (configurable via `VITE_API_URL`).

<details>
<summary>Legacy HTML UI (optional)</summary>

Open `FrontEnd/InvoiceSystem/*.html` files directly in the browser while the backend is running.
</details>

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
- [React frontend README](FrontEnd/invoice-ui/README.md)
- [Legacy HTML frontend README](FrontEnd/InvoiceSystem/README_FRONTEND.md)

---

## Roadmap

- [x] React SPA frontend with React Router
- [ ] Spring Security + JWT authentication
- [ ] Serve React build from Spring Boot `static/` for single-port deploy
- [ ] Unit & integration tests
- [ ] Docker Compose for MySQL + app

---

## License

MIT License — see [LICENSE](LICENSE).
