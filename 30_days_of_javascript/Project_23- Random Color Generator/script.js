// script.js
function generateColor() {
    const randomColor = '#' + Math.floor(Math.random() * 16777215).toString(16).padStart(6, '0');
    const colorDisplay = document.getElementById('color-display');
    const hexCode = document.getElementById('hex-code');

    colorDisplay.style.backgroundColor = randomColor;
    hexCode.textContent = randomColor;
}
