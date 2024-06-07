// JavaScript for Pomodoro Clock

let isRunning = false;
let isWorkSession = true;
let interval;
let minutes = 25;
let seconds = 0;

// DOM elements
const minutesDisplay = document.getElementById('minutes');
const secondsDisplay = document.getElementById('seconds');
const startPauseButton = document.getElementById('start-pause');
const resetButton = document.getElementById('reset');

// Update Timer Display
function updateDisplay() {
    minutesDisplay.textContent = minutes < 10 ? '0' + minutes : minutes;
    secondsDisplay.textContent = seconds < 10 ? '0' + seconds : seconds;
}

// Timer Countdown
function countdown() {
    if (seconds === 0) {
        if (minutes === 0) {
            clearInterval(interval);
            isWorkSession = !isWorkSession;
            isRunning = false;
            startPauseButton.textContent = 'Start';
            alert(isWorkSession ? 'Time for a break!' : 'Back to work!');
            resetTimer();
            return;
        } else {
            minutes--;
            seconds = 59;
        }
    } else {
        seconds--;
    }
    updateDisplay();
}

// Start or Pause Timer
function startPauseTimer() {
    if (isRunning) {
        clearInterval(interval);
        startPauseButton.textContent = 'Start';
    } else {
        interval = setInterval(countdown, 1000);
        startPauseButton.textContent = 'Pause';
    }
    isRunning = !isRunning;
}

// Reset Timer
function resetTimer() {
    clearInterval(interval);
    isRunning = false;
    minutes = isWorkSession ? 25 : 5;
    seconds = 0;
    startPauseButton.textContent = 'Start';
    updateDisplay();
}

// Event Listeners
startPauseButton.addEventListener('click', startPauseTimer);
resetButton.addEventListener('click', resetTimer);

// Initial Display
updateDisplay();
