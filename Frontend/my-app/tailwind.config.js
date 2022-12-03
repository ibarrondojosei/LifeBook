

/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["./src/**/*.{js,jsx,ts,tsx}"],
  theme: {
    container: {
      center: true,
    },
    maxWidth: {
      'custom': '1340px',
    },
    extend: {
      colors: {
        "celeste": "#2EC5CE",
        "light-celeste": "#96e2e6",
      }
    },
  },
  plugins: [],
};
