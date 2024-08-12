import React from 'react';
import lottie from "lottie-web";
import { defineElement } from "@lordicon/element";

defineElement(lottie.loadAnimation);

const NavAfter = ({ username, onLogout, onProfile }) => {
  return (
    <>
    <style>
      {`
        @media (max-width: 768px) {
          .nav-container {
            flex-direction: row;
            align-items: center;
            height: auto;
          }

          .hide{
            display: none ;
          }

          .nav-left {
            margin-bottom: 1rem;
          }

          .nav-right {
            flex-direction: row;
            align-items: center;
          }

          .nav-right button {
            margin: 0.5rem 0;
          }

          .logout{
            position: fixed ;
            right: 0 ;
          }
        }
      `}
    </style>
    <nav className="left-0 flex justify-between items-center bg-gray-800 text-white fixed w-full top-0 z-10 nav-container" style={{height: '4rem'}}>
      <div className="flex items-center nav-left hide">
        <span >
        <lord-icon
          src="https://cdn.lordicon.com/eeouelif.json"
          trigger="loop"
          delay="2000"
          colors="primary:#ffffff"
          style={{ width: '50px', height: '50px'  }}
        ></lord-icon>
        </span>
        <p>ExamPrep</p>
      </div>
      <div className="flex items-center nav-right">
        <lord-icon
          src="https://cdn.lordicon.com/hrjifpbq.json"
          trigger="loop-on-hover"
          delay="2000"
          colors="primary:#ffffff"
          style={{ width: '50px', height: '50px' }}
        />
        <button onClick={onProfile} className="ml-2 hover:bg-[#7aaef9]">{`${username}`}</button>
        <button
          onClick={onLogout}
          className="ml-4 px-4 py-2 logout bg-red-500 rounded-md hover:bg-red-800 focus:outline-none"
        >
          Logout
        </button>
      </div>
    </nav>
    </>
  );
};

export default NavAfter;
