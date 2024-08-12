import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import NavAfter from './NavAfter';

const SubjectQuiz = () => {
    const [difficulty, setDifficulty] = useState('easy');
    const navigate = useNavigate();
    const location = useLocation();
    const { origin, username } = location.state || { origin: 'quiz' };

    const handleLogout = () => {
        navigate('/login');
    };

    const handleStartQuiz = () => {
        navigate('/quiz', { state: { origin, difficulty, onTryAgain: origin, username, subject: origin } });
    };

    return (
        <>
            <NavAfter username={username} onLogout={handleLogout} />
            <div className="mt-8 min-h-screen min-w-screen flex-grow flex justify-center items-center bg-gradient-to-b from-blue-100 to-blue-500">
                <div className="w-full max-w-md bg-[#2b3353] p-8 rounded-lg shadow-lg text-white">
                    <h1 className="text-3xl font-bold mb-6 text-center">{origin.replace('-', ' ').toUpperCase()}</h1>
                    <div className="text-center">
                        <h2 className="text-2xl font-semibold mb-4">Instructions</h2>
                        <ul className='list-disc ml-4 text-left'>
                            <li>Time Limit: 10 minutes</li>
                            <li>Number of Questions: 10</li>
                        </ul>
                        <div className="mb-4 flex justify-between items-center">
                            <label htmlFor="difficulty" className="block mb-2 mt-2">Select Difficulty Level:</label>
                            <select
                                id="difficulty"
                                value={difficulty}
                                onChange={(e) => setDifficulty(e.target.value)}
                                className="w-20 cursor-pointer border rounded px-2 py-1 bg-gray-800 focus:outline-none focus:ring-2 focus:ring-indigo-500"
                            >
                                <option value="easy">Easy</option>
                                <option value="medium">Medium</option>
                                <option value="hard">Hard</option>
                            </select>
                        </div>
                        <div className="flex justify-between mt-6">
                            <button
                                onClick={() => navigate('/home', { state: { username: username } })}
                                className="px-4 py-2 bg-[#7530c6] text-white rounded-md font-bold hover:bg-blue-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-transform transform hover:scale-105"
                            >
                                Back to Home
                            </button>
                            <button
                                onClick={handleStartQuiz}
                                className="px-4 py-2 bg-[#7530c6] text-white rounded-md font-bold hover:bg-blue-500 focus:outline-none focus:ring-2 focus:ring-indigo-500 transition-transform transform hover:scale-105"
                            >
                                Start Quiz
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </>
    );
};

export default SubjectQuiz;
