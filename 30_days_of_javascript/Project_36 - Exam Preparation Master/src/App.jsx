import './App.css'
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import SignUpPage from './components/signup';
import LoginPage from './components/login';
import FirstPage from './components/First';
import HomePage from './components/HomePage';
import React from 'react';
import NavAfter from './components/NavAfter';
import Question from './components/Question';
import QuizResult from './components/result';
import SubjectQuiz from './components/SubjectQuiz';
import Profile from './components/profile';

function App() {

  const router = createBrowserRouter([
    {
      path: '/',
      element: <FirstPage/>
    },
    {
      path: '/signup',
      element: <SignUpPage/>
    },
    {
      path: '/login',
      element: <LoginPage/>
    },
    {
      path: '/home',
      element: <HomePage/>
    },
    {
      path: '/profile/:username',
      element: <Profile/>
    },
    {
      path: '/subject-quiz',
      element: <SubjectQuiz/>
    },
    {
      path: '/quiz',
      element: <Question/>
    },
    {
      path: '/result',
      element: <QuizResult/>
    },
  ])

  return (
    <>
      <RouterProvider router={router}/>
    </>
  )
}

export default App;