// script.js
let startTime;
let endTime;
let isTyping = false;

function startTyping() {
    if (!isTyping) {
        isTyping = true;
        startTime = new Date();
    }
    const userInput = document.getElementById('user-input').value;
    const textToType = document.getElementById('text-to-type').innerText;

    if (userInput === textToType) {
        endTime = new Date();
        calculateWPM();
        isTyping = false;
    }
}

function calculateWPM() {
    const timeDiff = (endTime - startTime) / 1000; // time in seconds
    const timeInMinutes = timeDiff / 60;
    const wordsTyped = document.getElementById('text-to-type').innerText.split(' ').length;
    const wpm = Math.round(wordsTyped / timeInMinutes);

    document.getElementById('wpm').innerText = `Words per minute: ${wpm}`;
    document.getElementById('time').innerText = `Time taken: ${timeDiff.toFixed(2)} seconds`;
}

function resetTest() {
    document.getElementById('user-input').value = '';
    document.getElementById('wpm').innerText = '';
    document.getElementById('time').innerText = '';
    isTyping = false;
}
