import React from 'react';
import NavAfter from './NavAfter'; 
import { useNavigate, useLocation } from 'react-router-dom';

const HomePage = () => {
  const location = useLocation();
  const { username } = location.state || {};
  const navigate = useNavigate();

  const handleLogout = () => {
    navigate('/login');
  };

  const handleProfile = () => {
    navigate(`/profile/${username}`);
  };

  const handleQuizClick = (quizType) => {
    navigate('/subject-quiz', { state: { origin: quizType, username: username } });
  };

  return (
    <>
    <style>
      {`
        .quiz-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 30px 60px -12px rgba(0, 0, 0, 0.25), 0 18px 36px -18px rgba(0, 0, 0, 0.3);
          }
      `}
    </style>
    <div className="text-[#1f2937] min-h-screen min-w-screen bg-gradient-to-b from-blue-100 to-blue-500">
      <NavAfter username={username} onLogout={handleLogout} onProfile={handleProfile} />
      <div className="container mx-auto py-20 px-4 text-center">
        <h1 className="text-5xl font-bold mb-16">Welcome to ExamPrep</h1>
        {username && <p className="text-2xl mb-4">Welcome back, {username}!</p>}
        <div className="flex flex-wrap justify-center gap-8">
          <div className="w-full sm:w-1/2 lg:w-1/3">
            <div
              className="quiz-card bg-white rounded-lg shadow-md cursor-pointer overflow-hidden"
              onClick={() => handleQuizClick('science-quiz')}
            >
              <img className="w-full h-48 object-cover" src="/sciencequiz.jpeg" alt="Science Quiz" />
              <h2 className="text-3xl font-semibold py-4">Science Quiz</h2>
            </div>
          </div>
          <div className="w-full sm:w-1/2 lg:w-1/3">
            <div
              className="quiz-card bg-white rounded-lg shadow-md cursor-pointer overflow-hidden"
              onClick={() => handleQuizClick('maths-quiz')}
            >
              <img className="w-full h-48 object-cover" src="/mathsquiz.jpeg" alt="Maths Quiz" />
              <h2 className="text-3xl font-semibold py-4">Maths Quiz</h2>
            </div>
          </div>
        </div>
      </div>
    </div>
    </>
  );
};

export default HomePage;
