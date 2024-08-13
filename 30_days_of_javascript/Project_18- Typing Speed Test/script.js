const msg = document.getElementById('msg');
const typedWords = document.getElementById('mywords');
const btn = document.getElementById('btn');
const errorDiv = document.getElementById('error');
const wpmDisplay = document.getElementById('wpm-display');
const progressBar = document.getElementById('progress-bar');
const car = document.getElementById('car');

let startTime, endTime, originalText = "";

const playGame = async () => {
    try {
        const response = await fetch('https://api.quotable.io/random');
        const data = await response.json();
        originalText = data.content;

        // Ensure the fetched text is not empty
        if (originalText) {
            msg.innerText = originalText;
            startTime = new Date().getTime();
            btn.innerText = "Done";
            typedWords.disabled = false;
            typedWords.focus();

            progressBar.style.width = "0%";
            car.style.left = "0%";
        } else {
            msg.innerText = "Failed to fetch a sentence. Please try again.";
        }
    } catch (error) {
        msg.innerText = "Error fetching sentence. Please check your internet connection.";
        console.error("Error fetching sentence:", error);
    }
}

const endGame = () => {
    endTime = new Date().getTime();
    const totalTime = (endTime - startTime) / 1000;
    const totalStr = typedWords.value;
    const wordCount = wordCounter(totalStr);
    const finalMsg = `You typed ${wordCount} words in ${totalTime} seconds.`;
    msg.innerText = finalMsg;
    btn.innerText = "Start";
    typedWords.value = '';
    typedWords.disabled = true;
    errorDiv.innerHTML = '';
    wpmDisplay.innerText = "WPM: 0";
    progressBar.style.width = "0%";
    car.style.left = "0%";
}

const wordCounter = (str) => {
    const words = str.trim().split(/\s+/).filter(Boolean);
    return words.length;
}

const calculateWPM = () => {
    const elapsedTime = (new Date().getTime() - startTime) / 1000 / 60; // convert to minutes
    const wordCount = wordCounter(typedWords.value);
    return Math.round(wordCount / elapsedTime);
}

const checkProgress = () => {
    const typedLength = typedWords.value.length;
    const totalLength = originalText.length;
    const progressPercentage = (typedLength / totalLength) * 100;

    progressBar.style.width = `${progressPercentage}%`;
    car.style.left = `calc(${progressPercentage}% - 25px)`;
}

const checkErrors = () => {
    const typedText = typedWords.value;
    const originalWords = originalText.split('');
    let mismatch = false;

    const result = originalWords.map((char, index) => {
        if (typedText[index] === char) {
            return char;
        } else {
            mismatch = true;
            return `<span class="mismatch">${char}</span>`;
        }
    }).join('');

    if (mismatch) {
        errorDiv.innerHTML = `Errors detected: <br>${result}`;
    } else {
        errorDiv.innerHTML = '';
    }
}

typedWords.addEventListener('input', () => {
    const wpm = calculateWPM();
    wpmDisplay.innerText = `WPM: ${wpm}`;
    checkErrors();
    checkProgress();
});

btn.addEventListener('click', function() {
    if (this.innerText === 'Start') {
        playGame();
    } else if (this.innerText === "Done") {
        endGame();
    }
});
