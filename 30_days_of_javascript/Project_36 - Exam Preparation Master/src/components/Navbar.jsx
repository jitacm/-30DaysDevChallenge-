import React from 'react';

const Navbar = () => {
  return (
    <nav className="left-0 flex justify-between items-center p-4 bg-gray-800 text-white fixed w-full top-0 z-10">
      <div className="text-2xl font-bold flex items-center">
        <lord-icon
          src="https://cdn.lordicon.com/eeouelif.json"
          trigger="loop"
          delay="2000"
          colors="primary:#ffffff"
          style={{ width: '50px', height: '50px' }}
        ></lord-icon>
        ExamPrep
      </div>
      <div className='gap-4 hidden md:flex'>
        <a href="/login" className="px-6 py-2 bg-[#7530c6] text-white rounded-md font-bold hover:text-white hover:bg-blue-500 mb-2">
          Login
        </a>
        <a href="/signup" className="px-6 py-2 bg-[#7530c6] text-white rounded-md font-bold hover:text-white hover:bg-blue-500 mb-2">
          Sign Up
        </a>
      </div>
    </nav>
  );
}

export default Navbar;
