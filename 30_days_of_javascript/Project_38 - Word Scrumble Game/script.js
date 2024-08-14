const words = [
    { word: "javascript", hint: "Programming language" },
    { word: "html", hint: "Markup language for web pages" },
    { word: "css", hint: "Style sheet language" },
    { word: "react", hint: "JavaScript library for building user interfaces" },
    { word: "python", hint: "Popular programming language for data science" }
];

let currentWord = "";
let scrambledWord = "";

// Function to scramble the word
function scramble(word) {
    let scrambled = word.split('').sort(() => Math.random() - 0.5).join('');
    while (scrambled === word) {
        scrambled = word.split('').sort(() => Math.random() - 0.5).join('');
    }
    return scrambled;
}

// Function to create a new word
function newWord() {
    const randomIndex = Math.floor(Math.random() * words.length);
    currentWord = words[randomIndex].word;
    scrambledWord = scramble(currentWord);
    document.getElementById('scrambleWord').innerText = scrambledWord;
    document.getElementById('hint').innerText = `Hint: ${words[randomIndex].hint}`;
    document.getElementById('input').value = "";
    document.getElementById('output').innerText = "Result:";
    document.getElementById('output').style.opacity = 0;
}

// Function to check if the guessed word is correct
function check() {
    const userInput = document.getElementById('input').value.trim().toLowerCase();
    const output = document.getElementById('output');

    if (userInput === currentWord) {
        output.innerText = "Correct! 🎉";
        output.style.color = "#27ae60";
        output.style.opacity = 1;
        output.classList.add('correct');
        setTimeout(() => {
            output.classList.remove('correct');
        }, 1000);
    } else {
        output.innerText = "Try Again! ❌";
        output.style.color = "#e74c3c";
        output.style.opacity = 1;
        output.classList.add('wrong');
        setTimeout(() => {
            output.classList.remove('wrong');
        }, 1000);
    }
}

// Function to refresh the game with a new word
function refresh() {
    newWord();
}

// Initialize the game with a new word when the page loads
window.onload = () => {
    newWord();
};
