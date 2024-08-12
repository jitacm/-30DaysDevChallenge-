import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';

const Profile = () => {
    const { username } = useParams();
    const [userData, setUserData] = useState(null);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        const fetchUserData = async () => {
            try {
                const response = await fetch(`http://localhost:3000/users/${username}`);
                if (response.ok) {
                    const data = await response.json();
                    setUserData(data);
                } else {
                    setError('User not found');
                }
            } catch (error) {
                setError('Error fetching user data');
            } finally {
                setLoading(false);
            }
        };

        fetchUserData();
    }, [username]);

    if (loading) {
        return <div className="text-white">Loading...</div>;
    }

    if (error) {
        return <div className="text-white">{error}</div>;
    }

    const toHome = () => {
        navigate('/home', { state: { username: username } });
    };

    return (
        <div className='flex justify-center items-center min-h-screen min-w-screen bg-gradient-to-b from-blue-100 to-blue-500'>
            <div className="w-full max-w-[60rem] bg-[#2b3353] shadow-lg flex flex-col rounded-lg p-8 relative z-10" style={{ height: '30rem' }}>
                <div className=" bg-purple-800 text-white p-4 rounded-lg mb-4">
                    <div className='flex justify-between'>
                        <strong className="profile text-gray-200 ">Profile</strong>
                        <button onClick={toHome} className="px-4 py-1 bg-[#2b3353] text-white rounded focus:outline-none focus:ring-2 hover:text-white hover:bg-[#91bafa] transition-transform transform hover:scale-105">Home</button >
                    </div>
                    <div className='flex'>
                        <p className="text-gray-200 flex flex-col w-20"><strong>Username</strong><strong>Email</strong> </p>
                        <div className='flex flex-col'>
                            <p>:</p>
                            <p>:</p>
                        </div>
                        <div className=' flex flex-col ml-2'>
                            <p className="text-gray-200">{userData && userData.username}</p>
                            <p className="text-gray-200">{userData && userData.email}</p>
                        </div>
                    </div>
                </div>
                <div className=" p-4 bg-gradient-to-b from-blue-300 to-blue-500 rounded-lg " style={{ height: '20rem' }}>
                    <table className="min-w-full table-auto border-collapse rounded-lg overflow-hidden" style={{ height: '16rem' }}>
                        <thead>
                            <tr>
                                <th className="p-4 text-left bg-purple-700 text-white">Subject</th>
                                <th className=" border-gray-200 p-4 text-left bg-purple-700 text-white">Easy</th>
                                <th className=" border-gray-200 p-4 text-left bg-purple-700 text-white">Medium</th>
                                <th className=" border-gray-200 p-4 text-left bg-purple-700 text-white">Hard</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr className="bg-purple-800">
                                <td className="p-4 text-gray-200">Maths</td>
                                <td className=" border-gray-200 p-4 text-gray-200">{userData && userData.maths_easy_score}</td>
                                <td className=" border-gray-200 p-4 text-gray-200">{userData && userData.maths_medium_score}</td>
                                <td className=" border-gray-200 p-4 text-gray-200">{userData && userData.maths_hard_score}</td>
                            </tr>
                            <tr className="bg-purple-700">
                                <td className="p-4 text-gray-200">Science</td>
                                <td className=" border-gray-200 p-4 text-gray-200">{userData && userData.science_easy_score}</td>
                                <td className=" border-gray-200 p-4 text-gray-200">{userData && userData.science_medium_score}</td>
                                <td className=" border-gray-200 p-4 text-gray-200">{userData && userData.science_hard_score}</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    );
    
};

export default Profile;
