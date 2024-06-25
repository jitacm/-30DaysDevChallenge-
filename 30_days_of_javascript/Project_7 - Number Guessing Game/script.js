let randomNumber = Math.floor(Math.random() * 100) + 1;
let attempts = 0;

document.getElementById('submitGuess').addEventListener('click', function() {
    const userGuess = Number(document.getElementById('guess').value);
    attempts++;
    
    let message = '';

    if (userGuess === randomNumber) {
        message = `Congratulations! You guessed the number in ${attempts} attempts.`;
        document.getElementById('submitGuess').disabled = true;
    } else if (userGuess > randomNumber) {
        message = 'Too high! Try again.';
    } else {
        message = 'Too low! Try again.';
    }

    document.getElementById('message').textContent = message;
});

document.getElementById('resetGame').addEventListener('click', function() {
    randomNumber = Math.floor(Math.random() * 100) + 1;
    attempts = 0;
    document.getElementById('guess').value = '';
    document.getElementById('message').textContent = 'Guess a number between 1 and 100:';
    document.getElementById('submitGuess').disabled = false;
});
