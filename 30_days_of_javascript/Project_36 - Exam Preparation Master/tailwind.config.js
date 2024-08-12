/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      minWidth: {
        'screen': '100vw',
        '-40': '-10rem', // Add negative value for spacing
      },spacing: {
        '120': '25rem', // Add a custom spacing value
      },colors: {
        customGray: '#242424',
      },width: {
        '100vw': '100vw',  // Add custom width value
      },
      height: {
        '90vh': '90vh',  // Add custom height value
      },
    },
  },
  plugins: [],
}