import { useEffect, useState } from 'react';
import { invoiceApi } from '../api/services';

const emptyPayment = {
  payment_date: '',
  payment_amount: '',
  payment_mode: '',
  payment_description: '',
};

export default function Invoices() {
  const [invoices, setInvoices] = useState([]);
  const [selectedInvoice, setSelectedInvoice] = useState(null);
  const [paymentForm, setPaymentForm] = useState(emptyPayment);
  const [payments, setPayments] = useState([]);
  const [modalInvoice, setModalInvoice] = useState(null);
  const [showModal, setShowModal] = useState(false);
  const [message, setMessage] = useState('');
  const [loading, setLoading] = useState(true);

  const loadInvoices = async () => {
    setLoading(true);
    try {
      const { data } = await invoiceApi.getAll();
      setInvoices(data);
    } catch {
      setMessage('Failed to load invoices.');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadInvoices();
  }, []);

  const selectForPayment = async (id) => {
    try {
      const { data } = await invoiceApi.getById(id);
      setSelectedInvoice(data);
      setPaymentForm(emptyPayment);
    } catch {
      setMessage('Failed to load invoice.');
    }
  };

  const submitPayment = async (e) => {
    e.preventDefault();
    if (!selectedInvoice) return;
    try {
      await invoiceApi.addPayment({
        details: { invoice_id: selectedInvoice.invoice_id },
        payment_date: paymentForm.payment_date,
        payment_amount: Number(paymentForm.payment_amount),
        payment_mode: paymentForm.payment_mode,
        payment_description: paymentForm.payment_description,
      });
      setMessage('Payment submitted successfully.');
      setSelectedInvoice(null);
      setPaymentForm(emptyPayment);
      await loadInvoices();
    } catch {
      setMessage('Failed to submit payment.');
    }
  };

  const viewInvoice = async (id) => {
    try {
      const [invoiceRes, paymentsRes] = await Promise.all([
        invoiceApi.getById(id),
        invoiceApi.getPayments(id),
      ]);
      setModalInvoice(invoiceRes.data);
      setPayments(paymentsRes.data);
      setShowModal(true);
    } catch {
      setMessage('Failed to load invoice details.');
    }
  };

  const statusClass = (status) => {
    if (status === 'Paid') return 'badge paid';
    if (status === 'Partial Paid') return 'badge partial';
    return 'badge unpaid';
  };

  return (
    <div className="page">
      <header className="page-header">
        <h2>Invoices</h2>
        <p>View invoices, record payments, and check history</p>
      </header>

      {message && <div className="alert">{message}</div>}

      {selectedInvoice && (
        <div className="card-panel payment-panel">
          <h3>Pay invoice #{selectedInvoice.invoice_id}</h3>
          <div className="payment-summary">
            <span>Customer: <strong>{selectedInvoice.customer_name}</strong></span>
            <span>Total: <strong>₹{selectedInvoice.total_amount}</strong></span>
            <span>Paid: <strong>₹{selectedInvoice.paid_amount}</strong></span>
            <span>Remaining: <strong>₹{selectedInvoice.remaining_amount}</strong></span>
          </div>
          <form className="form-row" onSubmit={submitPayment}>
            <label>
              Payment date
              <input
                type="date"
                value={paymentForm.payment_date}
                onChange={(e) => setPaymentForm((p) => ({ ...p, payment_date: e.target.value }))}
                required
              />
            </label>
            <label>
              Amount
              <input
                type="number"
                value={paymentForm.payment_amount}
                onChange={(e) => setPaymentForm((p) => ({ ...p, payment_amount: e.target.value }))}
                required
              />
            </label>
            <label>
              Mode
              <input
                value={paymentForm.payment_mode}
                onChange={(e) => setPaymentForm((p) => ({ ...p, payment_mode: e.target.value }))}
                required
              />
            </label>
            <label>
              Description
              <input
                value={paymentForm.payment_description}
                onChange={(e) => setPaymentForm((p) => ({ ...p, payment_description: e.target.value }))}
              />
            </label>
            <button type="submit" className="btn btn-primary">Submit payment</button>
            <button type="button" className="btn btn-secondary" onClick={() => setSelectedInvoice(null)}>
              Cancel
            </button>
          </form>
        </div>
      )}

      <div className="card-panel table-panel">
        <h3>All invoices</h3>
        {loading ? (
          <p className="muted">Loading...</p>
        ) : (
          <div className="table-wrap">
            <table>
              <thead>
                <tr>
                  <th>#</th>
                  <th>Date</th>
                  <th>Customer</th>
                  <th>Total</th>
                  <th>Paid</th>
                  <th>Remaining</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
              </thead>
              <tbody>
                {invoices.map((inv, index) => (
                  <tr key={inv.invoice_id}>
                    <td>{index + 1}</td>
                    <td>{inv.invoice_date}</td>
                    <td>{inv.customer_name}</td>
                    <td>₹{inv.total_amount}</td>
                    <td>₹{inv.paid_amount}</td>
                    <td>₹{inv.remaining_amount}</td>
                    <td><span className={statusClass(inv.status)}>{inv.status}</span></td>
                    <td className="action-cell">
                      {inv.status === 'Paid' ? (
                        <button type="button" className="btn btn-sm" onClick={() => viewInvoice(inv.invoice_id)}>
                          View
                        </button>
                      ) : (
                        <button type="button" className="btn btn-sm btn-primary" onClick={() => selectForPayment(inv.invoice_id)}>
                          Pay
                        </button>
                      )}
                      <button type="button" className="btn btn-sm btn-secondary" onClick={() => viewInvoice(inv.invoice_id)}>
                        Details
                      </button>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>

      {showModal && modalInvoice && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <header className="modal-header">
              <h3>Invoice #{modalInvoice.invoice_id}</h3>
              <button type="button" className="btn-close" onClick={() => setShowModal(false)}>×</button>
            </header>
            <div className="modal-body">
              <div className="detail-grid">
                <p><span>Customer</span> {modalInvoice.customer_name}</p>
                <p><span>Email</span> {modalInvoice.email_address}</p>
                <p><span>Mobile</span> {modalInvoice.mobile_number}</p>
                <p><span>Date</span> {modalInvoice.invoice_date}</p>
                <p><span>Total</span> ₹{modalInvoice.total_amount}</p>
                <p><span>Status</span> {modalInvoice.status}</p>
              </div>
              <h4>Payment history</h4>
              <div className="table-wrap">
                <table>
                  <thead>
                    <tr>
                      <th>ID</th>
                      <th>Amount</th>
                      <th>Date</th>
                      <th>Mode</th>
                      <th>Description</th>
                    </tr>
                  </thead>
                  <tbody>
                    {payments.length === 0 ? (
                      <tr><td colSpan="5" className="muted">No payments yet</td></tr>
                    ) : (
                      payments.map((p) => (
                        <tr key={p.payment_id}>
                          <td>{p.payment_id}</td>
                          <td>₹{p.payment_amount}</td>
                          <td>{p.payment_date}</td>
                          <td>{p.payment_mode}</td>
                          <td>{p.payment_description}</td>
                        </tr>
                      ))
                    )}
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
