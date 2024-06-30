// script.js
const apiKey = 'YOUR_API_KEY'; // Replace with your OMDB API key

async function getMovies() {
    const query = document.getElementById('query-input').value;
    if (query === '') return;

    const url = `http://www.omdbapi.com/?s=${query}&apikey=${apiKey}`;

    const response = await fetch(url);
    const data = await response.json();
    displayMovies(data.Search);
}

function displayMovies(movies) {
    const moviesDiv = document.getElementById('movies');
    moviesDiv.innerHTML = '';

    movies.forEach(movie => {
        const movieCard = document.createElement('div');
        movieCard.classList.add('movie-card');

        movieCard.innerHTML = `
            <img src="${movie.Poster}" alt="${movie.Title}">
            <h3>${movie.Title}</h3>
            <p>${movie.Year}</p>
        `;

        moviesDiv.appendChild(movieCard);
    });
}
