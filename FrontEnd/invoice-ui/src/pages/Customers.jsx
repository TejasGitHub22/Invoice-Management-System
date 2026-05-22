import { useEffect, useState } from 'react';
import { customerApi } from '../api/services';

const emptyForm = {
  customer_name: '',
  gender: 'MALE',
  birth_date: '',
  email_address: '',
  mobile_number: '',
  city: '',
};

export default function Customers() {
  const [customers, setCustomers] = useState([]);
  const [form, setForm] = useState(emptyForm);
  const [loading, setLoading] = useState(true);
  const [message, setMessage] = useState('');

  const loadCustomers = async () => {
    setLoading(true);
    try {
      const { data } = await customerApi.getAll();
      setCustomers(data);
    } catch {
      setMessage('Failed to load customers. Is the backend running on port 9001?');
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadCustomers();
  }, []);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!form.customer_name.trim()) {
      setMessage('Customer name is required.');
      return;
    }
    try {
      await customerApi.create(form);
      setForm(emptyForm);
      setMessage('Customer added successfully.');
      await loadCustomers();
    } catch {
      setMessage('Failed to add customer.');
    }
  };

  return (
    <div className="page">
      <header className="page-header">
        <h2>Customers</h2>
        <p>Manage customer records</p>
      </header>

      {message && <div className="alert">{message}</div>}

      <div className="split-layout">
        <form className="card-panel form-panel" onSubmit={handleSubmit}>
          <h3>Add customer</h3>
          <label>
            Name
            <input name="customer_name" value={form.customer_name} onChange={handleChange} required />
          </label>
          <fieldset className="gender-field">
            <legend>Gender</legend>
            <label>
              <input type="radio" name="gender" value="MALE" checked={form.gender === 'MALE'} onChange={handleChange} />
              Male
            </label>
            <label>
              <input type="radio" name="gender" value="FEMALE" checked={form.gender === 'FEMALE'} onChange={handleChange} />
              Female
            </label>
          </fieldset>
          <label>
            Birth date
            <input type="date" name="birth_date" value={form.birth_date} onChange={handleChange} />
          </label>
          <label>
            Email
            <input type="email" name="email_address" value={form.email_address} onChange={handleChange} />
          </label>
          <label>
            Mobile
            <input name="mobile_number" value={form.mobile_number} onChange={handleChange} />
          </label>
          <label>
            City
            <input name="city" value={form.city} onChange={handleChange} />
          </label>
          <button type="submit" className="btn btn-primary">Add customer</button>
        </form>

        <div className="card-panel table-panel">
          <h3>All customers</h3>
          {loading ? (
            <p className="muted">Loading...</p>
          ) : (
            <div className="table-wrap">
              <table>
                <thead>
                  <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Gender</th>
                    <th>Birth date</th>
                    <th>Email</th>
                    <th>Mobile</th>
                    <th>City</th>
                  </tr>
                </thead>
                <tbody>
                  {customers.map((c) => (
                    <tr key={c.customer_id}>
                      <td>{c.customer_id}</td>
                      <td>{c.customer_name}</td>
                      <td>{c.gender}</td>
                      <td>{c.birth_date}</td>
                      <td>{c.email_address}</td>
                      <td>{c.mobile_number}</td>
                      <td>{c.city}</td>
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
