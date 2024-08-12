import React, { useState, useRef } from 'react';
import Navbar from './Navbar';
import { useNavigate } from 'react-router-dom';

const SignUpPage = () => {
  const [form, setForm] = useState({ username: "", email: "", password: "", confirm_password: "" });
  const passwordRef2 = useRef(null);
  const navigate = useNavigate();
  const [error, setError] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const saveDetails = async () => {
    if (form.password === form.confirm_password) {
      try {
        const response = await fetch('http://localhost:3000/signup', {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            username: form.username,
            email: form.email,
            password: form.password
          }),
        });
        
        const result = await response.json();
        console.log(result) ;
        if (response.ok) {
          let req = await fetch("http://localhost:3000/users");
          let details = await req.json();
          const user = details.find(u => u.email === form.email);
          navigate('/home', { state: { username: form.username } });
        } else {
          setError(result.message);
        }
      } catch (error) {
        setError('An error occurred. Please try again.');
      }
    } else {
      setError('Passwords do not match');
      setForm({ ...form, confirm_password: "" });
      passwordRef2.current.focus();
    }
  };

  return (
    <div className="min-h-screen flex flex-col">
      <Navbar />
      <div className="mt-8 min-w-screen flex-grow flex text-left items-center justify-center bg-gradient-to-b from-blue-100 to-blue-500 text-gray-800">
        <div className="bg-shadow bg-[#95d2ff] p-10 rounded-lg shadow-lg w-full max-w-md">
          <h2 className="text-3xl font-bold mb-6 text-center text-gray-800">Sign Up for Free</h2>
          <p className="text-center text-gray-600 mb-8">Sign Up on ExamPrep and Begin Your Preparation Today!</p>
          {error && <p className="text-red-500 text-center mb-4">{error}</p>}
          <form>
            <div className="mb-4">
              <label htmlFor="username" className="block text-sm font-medium text-gray-700">
                Set Username
              </label>
              <input
                value={form.username}
                onChange={handleChange}
                placeholder='Enter your username'
                type="text"
                name="username"
                id="username"
                className="mt-1 text-white block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                required
              />
            </div>
            <div className="mb-4">
  <label htmlFor="email" className="block text-sm font-medium text-gray-700">
    Email
  </label>
  <input
    value={form.email}
    onChange={handleChange}
    placeholder='Enter your email'
    type="email"
    name="email"
    id="email"
    className="mt-1 text-white block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
    required
  />
</div>

<div className="mb-4">
  <label htmlFor="password" className="block text-sm font-medium text-gray-700">
    Set Password
  </label>
  <input
    value={form.password}
    onChange={handleChange}
    placeholder='Enter your password'
    type="password"
    name="password"
    id="password"
    className="mt-1 text-white block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
    required
  />
</div>

<div className="mb-6">
  <label htmlFor="confirm_password" className="block text-sm font-medium text-gray-700">
    Confirm Password
  </label>
  <input
    ref={passwordRef2}
    value={form.confirm_password}
    onChange={handleChange}
    placeholder='Confirm your password'
    type="password"
    name="confirm_password"
    id="confirm_password"
    className="mt-1 text-white block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
    required
  />
</div>

            {/* Email input field */}
            {/* Password input field */}
            {/* Confirm password input field */}
            <button
              type="button"
              onClick={saveDetails}
              className="w-full px-4 py-2 bg-indigo-600 text-white font-bold rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-transform transform hover:scale-105"
            >
              Sign Up
            </button>
          </form>
          <p className="text-center text-gray-600 mt-4">Already have an account? <a href="/login" className="text-indigo-600 hover:text-indigo-800">Login</a></p>
        </div>
      </div>
    </div>
  );
}

export default SignUpPage;
