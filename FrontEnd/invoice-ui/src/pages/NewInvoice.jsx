import { useEffect, useState } from 'react';
import { customerApi, invoiceApi, productApi } from '../api/services';

function lineTotal(rate, gst, qty) {
  return Number((((rate + (rate * gst) / 100) * qty)).toFixed(2));
}

export default function NewInvoice() {
  const [customers, setCustomers] = useState([]);
  const [products, setProducts] = useState([]);
  const [invoiceDate, setInvoiceDate] = useState('');
  const [customerId, setCustomerId] = useState('');
  const [productId, setProductId] = useState('');
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [quantity, setQuantity] = useState('');
  const [lineItems, setLineItems] = useState([]);
  const [message, setMessage] = useState('');

  useEffect(() => {
    const load = async () => {
      try {
        const [custRes, prodRes] = await Promise.all([customerApi.getAll(), productApi.getAll()]);
        setCustomers(custRes.data);
        setProducts(prodRes.data);
      } catch {
        setMessage('Failed to load customers or products.');
      }
    };
    load();
  }, []);

  const handleProductChange = async (e) => {
    const id = e.target.value;
    setProductId(id);
    if (!id) {
      setSelectedProduct(null);
      return;
    }
    try {
      const { data } = await productApi.getById(id);
      setSelectedProduct(data);
      setQuantity('');
    } catch {
      setMessage('Failed to load product details.');
    }
  };

  const computedTotal = selectedProduct && quantity
    ? lineTotal(selectedProduct.product_rate, selectedProduct.product_gst, Number(quantity))
    : 0;

  const addLineItem = () => {
    if (!selectedProduct || !quantity) {
      setMessage('Select a product and enter quantity.');
      return;
    }
    setLineItems((prev) => [
      ...prev,
      {
        product_name: selectedProduct.product_name,
        product: { product_id: selectedProduct.product_id },
        rate: selectedProduct.product_rate,
        gst: selectedProduct.product_gst,
        quantity: Number(quantity),
        total: computedTotal,
      },
    ]);
    setProductId('');
    setSelectedProduct(null);
    setQuantity('');
    setMessage('');
  };

  const finalAmount = lineItems.reduce((sum, item) => sum + item.total, 0);

  const submitInvoice = async () => {
    if (!invoiceDate || !customerId || lineItems.length === 0) {
      setMessage('Date, customer, and at least one product are required.');
      return;
    }
    try {
      await invoiceApi.create({
        customer: { customer_id: Number(customerId) },
        total_amount: Math.round(finalAmount),
        invoice_date: invoiceDate,
        invoiceprdt: lineItems.map(({ product, quantity }) => ({
          quantity,
          product,
        })),
      });
      setMessage('Invoice created successfully.');
      setLineItems([]);
      setInvoiceDate('');
      setCustomerId('');
    } catch {
      setMessage('Failed to create invoice.');
    }
  };

  return (
    <div className="page">
      <header className="page-header">
        <h2>New Invoice</h2>
        <p>Create an invoice with multiple line items and GST</p>
      </header>

      {message && <div className="alert">{message}</div>}

      <div className="card-panel">
        <div className="form-row">
          <label>
            Invoice date
            <input type="date" value={invoiceDate} onChange={(e) => setInvoiceDate(e.target.value)} />
          </label>
          <label>
            Customer
            <select value={customerId} onChange={(e) => setCustomerId(e.target.value)}>
              <option value="">Select customer</option>
              {customers.map((c) => (
                <option key={c.customer_id} value={c.customer_id}>
                  {c.customer_name}
                </option>
              ))}
            </select>
          </label>
        </div>

        <hr className="divider" />

        <div className="invoice-line-form">
          <label>
            Product
            <select value={productId} onChange={handleProductChange}>
              <option value="">Select product</option>
              {products.map((p) => (
                <option key={p.product_id} value={p.product_id}>
                  {p.product_name}
                </option>
              ))}
            </select>
          </label>
          <label>
            Rate
            <input value={selectedProduct?.product_rate ?? ''} disabled />
          </label>
          <label>
            GST %
            <input value={selectedProduct?.product_gst ?? ''} disabled />
          </label>
          <label>
            Stock
            <input value={selectedProduct?.product_stock ?? ''} disabled />
          </label>
          <label>
            Quantity
            <input
              type="number"
              min="1"
              value={quantity}
              onChange={(e) => setQuantity(e.target.value)}
            />
          </label>
          <label>
            Total
            <input value={computedTotal || ''} disabled />
          </label>
          <button type="button" className="btn btn-primary" onClick={addLineItem}>
            + Add line
          </button>
        </div>

        <div className="table-wrap">
          <table>
            <thead>
              <tr>
                <th>#</th>
                <th>Product</th>
                <th>Rate</th>
                <th>GST</th>
                <th>Qty</th>
                <th>Total</th>
              </tr>
            </thead>
            <tbody>
              {lineItems.map((item, index) => (
                <tr key={index}>
                  <td>{index + 1}</td>
                  <td>{item.product_name}</td>
                  <td>₹{item.rate}</td>
                  <td>{item.gst}%</td>
                  <td>{item.quantity}</td>
                  <td>₹{item.total}</td>
                </tr>
              ))}
            </tbody>
            <tfoot>
              <tr>
                <td colSpan="5" className="text-right">
                  <strong>Grand total</strong>
                </td>
                <td>
                  <strong>₹{finalAmount.toFixed(2)}</strong>
                </td>
              </tr>
            </tfoot>
          </table>
        </div>

        <button type="button" className="btn btn-primary" onClick={submitInvoice} disabled={lineItems.length === 0}>
          Submit invoice
        </button>
      </div>
    </div>
  );
}
