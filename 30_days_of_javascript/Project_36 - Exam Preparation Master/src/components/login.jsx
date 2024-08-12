import React, { useState } from 'react';
import Navbar from './Navbar';
import { useNavigate } from 'react-router-dom';

const LoginPage = () => {
  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false); // Added loading state
  const navigate = useNavigate();

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch("http://localhost:3000/login", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          email: form.email,
          password: form.password,
        }),
      });
      const result = await response.json();
      if (response.ok) {
        navigate('/home', { state: { username: result.username } });
        console.log("Login successful ->", result.username);
      } else {
        setError(result.message);
      }
    } catch (error) {
      console.log(error);
      setError("An error occurred. Please try again.");
    }
  };
  

  return (
    <div className="min-h-screen flex flex-col">
      <Navbar />
      <div className="min-w-screen flex-grow flex text-left items-center justify-center bg-gradient-to-b from-blue-100 to-blue-500 text-gray-800">
        <div className="bg-shadow bg-[#95d2ff] p-10 rounded-lg shadow-lg w-full max-w-md">
          <h2 className="text-2xl font-bold mb-6 text-center text-black">Welcome Back! Let's Start</h2>
          {error && <p className="text-red-500 text-center">{error}</p>}
          <form className='text-left' onSubmit={handleLogin}>
            <div className="mb-4">
              <label htmlFor="email" className="block text-sm font-medium text-gray-700">
                Email
              </label>
              <input
                value={form.email}
                onChange={handleChange}
                placeholder='Enter Email'
                type="email"
                name="email"
                id="email"
                className="text-white mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                required
              />
            </div>
            <div className="mb-6">
              <label htmlFor="password" className="block text-sm font-medium text-gray-700">
                Password
              </label>
              <input
                value={form.password}
                onChange={handleChange}
                placeholder='Enter Password'
                type="password"
                name="password"
                id="password"
                className="text-white mt-1 block w-full px-3 py-2 border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm"
                required
              />
            </div>
            <button
              type="submit"
              disabled={loading} // Disable button during loading state
              className={`w-full px-4 py-2 bg-indigo-600 text-white font-bold rounded-md hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-transform transform hover:scale-105 ${loading ? 'opacity-50 cursor-not-allowed' : ''}`}
            >
              {loading ? 'Logging in...' : 'Login'}
            </button>
          </form>
          <p className="text-center text-gray-600 mt-4">New User? <a href="/signup" className="text-indigo-600 hover:text-indigo-800">Sign Up</a></p>
        </div>
      </div>
    </div>
  );
}

export default LoginPage;
