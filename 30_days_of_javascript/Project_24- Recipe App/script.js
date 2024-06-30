// script.js
const appId = 'YOUR_APP_ID'; // Replace with your Edamam Recipe Search API app ID
const appKey = 'YOUR_APP_KEY'; // Replace with your Edamam Recipe Search API app key

async function getRecipes() {
    const query = document.getElementById('query-input').value;
    if (query === '') return;

    const url = `https://api.edamam.com/search?q=${query}&app_id=${appId}&app_key=${appKey}&to=10`;

    const response = await fetch(url);
    const data = await response.json();
    displayRecipes(data.hits);
}

function displayRecipes(recipes) {
    const recipesDiv = document.getElementById('recipes');
    recipesDiv.innerHTML = '';

    recipes.forEach(recipe => {
        const recipeCard = document.createElement('div');
        recipeCard.classList.add('recipe-card');

        recipeCard.innerHTML = `
            <img src="${recipe.recipe.image}" alt="${recipe.recipe.label}">
            <h3>${recipe.recipe.label}</h3>
            <p>${recipe.recipe.source}</p>
            <a href="${recipe.recipe.url}" target="_blank">View Recipe</a>
        `;

        recipesDiv.appendChild(recipeCard);
    });
}
