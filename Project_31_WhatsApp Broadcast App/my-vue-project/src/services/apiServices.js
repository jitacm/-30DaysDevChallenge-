// src/services/apiService.js
import axios from 'axios';

const API_BASE_URL = 'http://localhost:8000';  // Update this URL to your deployed backend URL

export const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const getTemplates = () => api.get('/templates');
export const createContact = (contact) => api.post('/contacts/', contact);
export const getContacts = (params) => api.get('/contacts/', { params });
export const getContactByPhone = (phone) => api.get(`/contacts/phone/${phone}`);
export const updateContact = (contactId, contact) => api.put(`/contacts/${contactId}`, contact);
export const deleteContact = (phone) => api.delete(`/contacts/${phone}`);
export const sendTemplateMessage = (data) => api.post('/send-template-message/', data);
