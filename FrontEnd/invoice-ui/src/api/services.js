import api from './client';

export const customerApi = {
  getAll: () => api.get('/api/customer'),
  create: (data) => api.post('/api/customer', data),
  getById: (id) => api.get(`/api/customer/${id}`),
  update: (id, data) => api.put(`/api/customer/${id}`, data),
  delete: (id) => api.delete(`/api/customer/${id}`),
};

export const productApi = {
  getAll: () => api.get('/api/product'),
  create: (data) => api.post('/api/product', data),
  getById: (id) => api.get(`/api/product/${id}`),
  update: (id, data) => api.put(`/api/product/${id}`, data),
  delete: (id) => api.delete(`/api/product/${id}`),
};

export const invoiceApi = {
  getAll: () => api.get('/api/allinvoices'),
  getById: (id) => api.get(`/api/invoice/${id}`),
  create: (data) => api.post('/api/newinvoice', data),
  addPayment: (data) => api.post('/api/addpayment', data),
  getPayments: (id) => api.get(`/api/getinvoicepayments/${id}`),
};
