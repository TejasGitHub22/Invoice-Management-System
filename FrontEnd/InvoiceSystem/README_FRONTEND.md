# Invoice Pro – Frontend

## 1. Overview
This is the **frontend interface** for the **Invoice Pro** system. It is built using plain HTML, CSS (Bootstrap), and JavaScript.  
It connects to the backend REST API (`http://localhost:9001/api/...`) and allows you to manage **customers, products, invoices, and payments** through a browser.  

---

## 2. Features

### **1. Customer Management** (`Customer.html`)
- **Add New Customers**:  
  Enter customer details such as name, gender, birth date, email, mobile number, and city.  
  Data is sent to the backend using a `POST /api/customer` request.
- **View All Customers**:  
  A table automatically fetches (`GET /api/customer`) and displays all existing customers, showing:
  - Customer ID
  - Name
  - Gender
  - Birth Date
  - Email
  - Mobile Number
  - City
- **Form Validation**:  
  Prevents sending empty values for required fields like name and gender.

---

### **2. Product Management** (`Product.html`)
- **Add New Products**:  
  Fill in details like product name, rate, GST percentage, and available stock.  
  Submits data to `POST /api/product`.
- **View Product List**:  
  Automatically fetches (`GET /api/product`) and lists:
  - Product ID
  - Name
  - Rate
  - GST
  - Stock
- **Easy Inventory Updates**:  
  New products are instantly added to the list without reloading the page.

---

### **3. Invoice Management**

#### **a. All Invoices** (`AllInvoices.html`)
- **View All Invoices**:  
  Fetches (`GET /api/allinvoices`) and lists each invoice with:
  - Invoice Date
  - Customer Name
  - Total Amount
  - Paid Amount
  - Remaining Amount
  - Status (Paid/Unpaid)
- **Pay Pending Invoices**:  
  Click **Pay Now** to:
  - View the invoice summary
  - Enter payment date, amount, mode, and description
  - Submit payment via `POST /api/addpayment`
- **View Invoice Details**:  
  Click **View Now** to open a modal showing:
  - Customer details
  - Invoice metadata
  - Payment history fetched from `GET /api/getinvoicepayments/{id}`

---

#### **b. New Invoice** (`NewInvoice.html`)
- **Select Customer**:  
  Dropdown list populated from `GET /api/customer`.
- **Add Products to Invoice**:
  - Select a product from dropdown (populated from `GET /api/product`)
  - Auto-fills product rate, GST, and stock
  - Enter quantity → Auto-calculates total with GST
  - Add multiple products to the invoice list
- **Invoice Total Calculation**:  
  Displays cumulative total for all selected products.
- **Submit Invoice**:  
  Sends invoice details with linked products to `POST /api/newinvoice`.
- **Real-Time Table Update**:  
  Displays products being added before final submission.

---

## 3. File Structure
```
frontend/
 ├── AllInvoices.html   # View & pay invoices, check payment history
 ├── Customer.html      # Manage customer records
 ├── NewInvoice.html    # Create new invoices
 ├── Product.html       # Manage products
```

---

## 4. Requirements
- Any modern browser (Chrome, Edge, Firefox, etc.)
- Backend running locally at `http://localhost:9001`
- Internet connection for Bootstrap & jQuery CDN

---

## 5. How to Use
1. **Start Backend**  
   Ensure Invoice Pro backend is running on port 9001.
2. **Open Pages in Browser**  
   - `Customer.html` → Manage customers  
   - `Product.html` → Manage products  
   - `NewInvoice.html` → Create invoices  
   - `AllInvoices.html` → View/pay invoices  
3. **Navigate Using Sidebar**  
   Each page has a left-side navigation menu.

---

## 6. API Endpoints Used
- `GET /api/customer`  
- `POST /api/customer`  
- `GET /api/product`  
- `POST /api/product`  
- `GET /api/invoice/{id}`  
- `GET /api/allinvoices`  
- `POST /api/newinvoice`  
- `POST /api/addpayment`  
- `GET /api/getinvoicepayments/{id}`  

---

## 7. Tech Stack
- **HTML5**
- **CSS3** (Bootstrap 5.0.2)
- **JavaScript** (Fetch API)
- **jQuery** (for dynamic table rendering)
- **Bootstrap Modals** (for viewing invoice details)
