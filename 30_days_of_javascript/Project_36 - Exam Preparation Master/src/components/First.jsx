import React from 'react';
import Navbar from './Navbar';
import { useNavigate } from 'react-router-dom';

const FirstPage = () => {
  const navigate = useNavigate();

  return (
    <>
      <Navbar />
      <div className="flex flex-col min-w-[100vw] justify-center items-center min-h-screen bg-gradient-to-b from-blue-100 to-blue-500 text-gray-800">
        <div className="bg-[#2b3353] p-10 rounded-lg shadow-lg max-w-lg text-center">
          <h1 className="text-4xl font-bold mb-4 text-white">Welcome to ExamPrep!</h1>
          <p className="text-xl text-white mb-6">
            Your ultimate platform for exam preparation. Get ready to ace your exams with our tailored quizzes in Science and Maths.
          </p>
          <div className="mb-6">
            <h2 className="text-2xl font-semibold mb-2 text-white">Features:</h2>
            <ul className="list-disc list-inside text-left text-white">
              <li>Practice Science and Maths quizzes.</li>
              <li>Choose your difficulty level: Easy, Medium, Hard.</li>
              <li>Track your progress and improve your scores.</li>
              <li>Join a community of learners and achieve your goals.</li>
            </ul>
          </div>
          <button
            onClick={() => navigate('/signup')}
            className="px-6 py-2 bg-[#7530c6] text-white rounded-md font-bold hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-transform transform hover:scale-105 mb-2"
            aria-label="Get Started"
          >
            Get Started
          </button>
          <p className="text-white mt-4">
            Already have an account? <a href="/login" className="text-indigo-600 hover:text-indigo-800" aria-label="Login">Login</a>
          </p>
        </div>
      </div>
    </>
  );
};

export default FirstPage;
