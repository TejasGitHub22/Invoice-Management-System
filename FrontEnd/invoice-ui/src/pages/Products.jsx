import { useEffect, useState } from 'react';
import { productApi } from '../api/services';

const emptyForm = {
  product_name: '',
  product_rate: '',
  product_gst: '',
  product_stock: '',
};

export default function Products() {
  const [products, setProducts] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState('');

  const loadProducts = async () => {
    setLoading(true);
    try {
      const { data } = await productApi.getAll();
      setProducts(data);
    } catch {
      setMessage('Failed to load products. Is the backend running?');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadProducts();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.product_name.trim()) {
      setMessage('Product name is required.');
      return;
    }
    try {
      await productApi.create({
        product_name: form.product_name,
        product_rate: Number(form.product_rate),
        product_gst: Number(form.product_gst),
        product_stock: Number(form.product_stock),
      });
      setForm(emptyForm);
      setMessage('Product added successfully.');
      await loadProducts();
    } catch {
      setMessage('Failed to add product.');
    }
  };

  return (
    <div className="page">
      <header className="page-header">
        <h2>Products</h2>
        <p>Manage product catalog with GST and stock</p>
      </header>

      {message && <div className="alert">{message}</div>}

      <div className="split-layout">
        <form className="card-panel form-panel" onSubmit={handleSubmit}>
          <h3>Add product</h3>
          <label>
            Name
            <input name="product_name" value={form.product_name} onChange={handleChange} required />
          </label>
          <label>
            Rate (₹)
            <input type="number" name="product_rate" value={form.product_rate} onChange={handleChange} required />
          </label>
          <label>
            GST (%)
            <input type="number" name="product_gst" value={form.product_gst} onChange={handleChange} required />
          </label>
          <label>
            Stock
            <input type="number" name="product_stock" value={form.product_stock} onChange={handleChange} required />
          </label>
          <button type="submit" className="btn btn-primary">Add product</button>
        </form>

        <div className="card-panel table-panel">
          <h3>All products</h3>
          {loading ? (
            <p className="muted">Loading...</p>
          ) : (
            <div className="table-wrap">
              <table>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Rate</th>
                    <th>GST %</th>
                    <th>Stock</th>
                  </tr>
                </thead>
                <tbody>
                  {products.map((p) => (
                    <tr key={p.product_id}>
                      <td>{p.product_id}</td>
                      <td>{p.product_name}</td>
                      <td>₹{p.product_rate}</td>
                      <td>{p.product_gst}%</td>
                      <td>{p.product_stock}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}
