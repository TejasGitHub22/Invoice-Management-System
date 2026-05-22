import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { customerApi, invoiceApi, productApi } from '../api/services';

export default function Dashboard() {
  const [stats, setStats] = useState({ customers: 0, products: 0, invoices: 0, unpaid: 0 });
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const load = async () => {
      try {
        const [customers, products, invoices] = await Promise.all([
          customerApi.getAll(),
          productApi.getAll(),
          invoiceApi.getAll(),
        ]);
        const unpaid = invoices.data.filter((i) => i.status !== 'Paid').length;
        setStats({
          customers: customers.data.length,
          products: products.data.length,
          invoices: invoices.data.length,
          unpaid,
        });
      } catch {
        setStats({ customers: 0, products: 0, invoices: 0, unpaid: 0 });
      } finally {
        setLoading(false);
      }
    };
    load();
  }, []);

  const cards = [
    { label: 'Customers', value: stats.customers, to: '/customers', color: 'blue' },
    { label: 'Products', value: stats.products, to: '/products', color: 'green' },
    { label: 'Invoices', value: stats.invoices, to: '/invoices', color: 'purple' },
    { label: 'Pending payments', value: stats.unpaid, to: '/invoices', color: 'orange' },
  ];

  return (
    <div className="page">
      <header className="page-header">
        <h2>Dashboard</h2>
        <p>Overview of your invoicing workspace</p>
      </header>

      {loading ? (
        <p className="muted">Loading...</p>
      ) : (
        <div className="stat-grid">
          {cards.map((card) => (
            <Link key={card.label} to={card.to} className={`stat-card stat-${card.color}`}>
              <span className="stat-value">{card.value}</span>
              <span className="stat-label">{card.label}</span>
            </Link>
          ))}
        </div>
      )}

      <section className="card-panel">
        <h3>Quick actions</h3>
        <div className="action-row">
          <Link to="/new-invoice" className="btn btn-primary">Create invoice</Link>
          <Link to="/customers" className="btn btn-secondary">Add customer</Link>
          <Link to="/products" className="btn btn-secondary">Add product</Link>
        </div>
      </section>
    </div>
  );
}
