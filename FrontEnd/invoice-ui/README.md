# Invoice Pro — React Frontend

Modern **React 19** + **Vite** SPA for the Invoice Management System backend.

## Tech stack

- React 19
- React Router
- Axios
- Vite (dev server + proxy)

## Setup

```bash
npm install
cp .env.example .env
npm run dev
```

App runs at **http://localhost:5173**

API requests go to `VITE_API_URL` (default `http://localhost:9001`). Vite proxies `/api` to the backend during development.

## Scripts

| Command | Description |
|---------|-------------|
| `npm run dev` | Start dev server |
| `npm run build` | Production build → `dist/` |
| `npm run preview` | Preview production build |

## Pages

| Route | Feature |
|-------|---------|
| `/` | Dashboard with KPI cards |
| `/customers` | Add & list customers |
| `/products` | Add & list products |
| `/new-invoice` | Multi-line invoice with GST |
| `/invoices` | List, pay, view payment history |

## Prerequisites

Start the Spring Boot backend first:

```bash
cd ../../BackEnd/InvoiceSystem
mvn spring-boot:run
```
