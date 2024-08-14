import React, { useEffect, useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';

const QuizResult = () => {
    const location = useLocation();
    const navigate = useNavigate();
    const { correct, incorrect, notAttempted, questions, tryKey, username, subject, level } = location.state || {};
    const score = ((correct * 4) - incorrect);
    const [bestScore, setBestScore] = useState(null);
    const subjectName = subject.split('-')[0];

    useEffect(() => {
        const getBestScore = async () => {
            try {
                const response = await fetch('http://localhost:3000/users');
                const details = await response.json();
                const user = details.find(u => u.username === username);

                if (user) {
                    const scoreKey = `${subjectName}_${level}_score`;
                    setBestScore(user[scoreKey] || '-');
                }
            } catch (error) {
                console.error("Error fetching user data:", error);
            }
        };

        const updateBestScore = async () => {
            try {
                const response = await fetch('http://localhost:3000/update-score', {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: username,
                        subject: subject,
                        level: level,
                        score: score
                    })
                });
                const result = await response.json();
                if (result.success) {
                    getBestScore(); // Fetch the updated best score
                } else {
                    console.error("Failed to update score:", result.message);
                }
            } catch (error) {
                console.error("Error updating score:", error);
            }
        };

        if (username && subject && level) {
            getBestScore();
            updateBestScore();
        }
    }, [username, subject, level, score, subjectName]);

    if (!questions) {
        return <div className="text-white">No data available.</div>;
    }

    const tryAgain = () => {
        navigate('/subject-quiz', { state: { origin: tryKey, username: username } });
    };

    const toHome = () => {
        navigate('/home', { state: { username: username } });
    }

    return (
        <div className='mt-8 min-h-screen min-w-screen flex-grow flex text-left items-center justify-center bg-gradient-to-b from-blue-100 to-blue-500'>
            <style>
                {`
                    #root {
                        margin: 0;
                        padding: 0;
                    }

                    body {
                        overflow: hidden;
                    }

                    .scrollbar::-webkit-scrollbar {
                        width: 12px;
                    }

                    .scrollbar::-webkit-scrollbar-thumb {
                        background-color: white;
                        border-radius: 6px;
                    }

                    .scrollbar::-webkit-scrollbar-track {
                        background-color: transparent;
                    }

                    .key-link {
                        display: inline-block;
                        padding: 0.0rem 1rem;
                        background-color: #a964ff4a;
                        border-radius: 0.2rem;
                        color: white;
                        transition: transform 0.3s ease;
                    }

                    .key-link:hover {
                        color: white;
                        transform: scale(1.1);
                    }
                `}
            </style>
            <div className="w-full max-w-[40rem] bg-[#2b3353] shadow-lg flex flex-col rounded-lg h-[30rem] p-4 relative z-10 text-left">
                <div className="bg-purple-800 text-white p-4 rounded-lg mb-4 flex justify-between">
                    <div>
                        <h3 className="text-gray-200 mb-2">Quiz Result</h3>
                        <p className="text-gray-200">Marks obtained: {score}</p>
                        <p className="text-gray-200">No. of answers correct: {correct}</p>
                        <p className="text-gray-200">No. of answers incorrect: {incorrect}</p>
                        <p className="text-gray-200">No. of questions not attempted: {notAttempted}</p>
                        <p className="text-gray-200">Best Score ({`Subject: ${subjectName}, Level: ${level}`}): {bestScore}</p>
                    </div>
                    <div className='flex flex-col justify-between text-center'>
                        <button onClick={toHome} className="px-4 py-1 bg-[#2b3353] text-white rounded focus:outline-none focus:ring-2 hover:text-white hover:bg-indigo-600 transition-transform transform hover:scale-105">Home</button>
                        <button onClick={tryAgain} className="px-4 py-1 bg-[#2b3353] text-white rounded focus:outline-none focus:ring-2 hover:text-white hover:bg-indigo-600 transition-transform transform hover:scale-105">Try Again</button>
                    </div>
                </div>
                <div className="overflow-y-scroll scrollbar p-4">
                    {questions.map((question, index) => (
                        <div key={index} className="p-4 mb-4 bg-purple-100 rounded-lg">
                            <p className="text-gray-800" dangerouslySetInnerHTML={{ __html: `${(index + 1)}. ${question.question}` }}></p>
                            <ul className="list-disc ml-8">
                                {question.choices.map((choice, choiceIndex) => {
                                    let className = 'text-gray-600';
                                    if (!question.selected && choice === question.correct_answer) {
                                        className = 'text-purple-600';
                                    } else if (choice === question.correct_answer) {
                                        className = 'text-green-600';
                                    } else if (choice === question.selected) {
                                        className = 'text-red-600';
                                    }
                                    return (
                                        <li key={choiceIndex} className={className} dangerouslySetInnerHTML={{ __html: choice }}></li>
                                    );
                                })}
                            </ul>
                            <p className="text-gray-800">
                                {question.selected ? (
                                    question.selected === question.correct_answer
                                        ? "Correct"
                                        : "Incorrect"
                                ) : "Not Answered"}
                            </p>
                        </div>
                    ))}
                </div>
            </div>
        </div>
    );
};

export default QuizResult;
