const quizData = [
    {
        question: "What is the capital of France?",
        answers: {
            a: "Berlin",
            b: "Madrid",
            c: "Paris"
        },
        correctAnswer: "c"
    },
    {
        question: "Which language is used for web apps?",
        answers: {
            a: "Python",
            b: "JavaScript",
            c: "Java"
        },
        correctAnswer: "b"
    },
    {
        question: "Who is the President of the US in 2024?",
        answers: {
            a: "Joe Biden",
            b: "Donald Trump",
            c: "Kamala Harris"
        },
        correctAnswer: "a"
    },
    {
        question: "What is the largest planet in our solar system?",
        answers: {
            a: "Earth",
            b: "Jupiter",
            c: "Saturn"
        },
        correctAnswer: "b"
    },
    {
        question: "What year did the Titanic sink?",
        answers: {
            a: "1912",
            b: "1905",
            c: "1915"
        },
        correctAnswer: "a"
    },
    {
        question: "What is the smallest country in the world?",
        answers: {
            a: "Monaco",
            b: "San Marino",
            c: "Vatican City"
        },
        correctAnswer: "c"
    },
    {
        question: "Who wrote 'To Kill a Mockingbird'?",
        answers: {
            a: "Harper Lee",
            b: "Mark Twain",
            c: "Ernest Hemingway"
        },
        correctAnswer: "a"
    },
    {
        question: "What is the chemical symbol for gold?",
        answers: {
            a: "Au",
            b: "Ag",
            c: "Pb"
        },
        correctAnswer: "a"
    },
    {
        question: "In which year did the Berlin Wall fall?",
        answers: {
            a: "1989",
            b: "1991",
            c: "1985"
        },
        correctAnswer: "a"
    },
    {
        question: "What is the main ingredient in guacamole?",
        answers: {
            a: "Tomato",
            b: "Avocado",
            c: "Pepper"
        },
        correctAnswer: "b"
    }
];


let timer;
let timeLeft = 600; // 10 minutes in seconds

function buildQuiz() {
    const quizContainer = document.getElementById('quiz');
    const output = [];

    quizData.forEach((currentQuestion, questionNumber) => {
        const answers = [];
        for (const letter in currentQuestion.answers) {
            answers.push(
                `<label>
                    <input type="radio" name="question${questionNumber}" value="${letter}">
                    ${letter} : ${currentQuestion.answers[letter]}
                </label>`
            );
        }

        output.push(
            `<div class="question">${currentQuestion.question}</div>
             <div class="answers">${answers.join('')}</div>`
        );
    });

    quizContainer.innerHTML = output.join('');
    updateProgress(0); // Initialize progress bar
}

function showResults() {
    const quizContainer = document.getElementById('quiz');
    const answerContainers = quizContainer.querySelectorAll('.answers');
    const resultsContainer = document.getElementById('results');

    let numCorrect = 0;

    quizData.forEach((currentQuestion, questionNumber) => {
        const answerContainer = answerContainers[questionNumber];
        const selector = `input[name=question${questionNumber}]:checked`;
        const userAnswer = (answerContainer.querySelector(selector) || {}).value;

        if (userAnswer === currentQuestion.correctAnswer) {
            numCorrect++;
            answerContainers[questionNumber].style.color = 'green';
        } else {
            answerContainers[questionNumber].style.color = 'red';
        }
    });

    resultsContainer.innerHTML = `${numCorrect} out of ${quizData.length}`;
}

function startTimer() {
    timer = setInterval(() => {
        if (timeLeft <= 0) {
            clearInterval(timer);
            document.getElementById('submit').click();
            return;
        }

        let minutes = Math.floor(timeLeft / 60);
        let seconds = timeLeft % 60;
        if (seconds < 10) seconds = '0' + seconds;

        document.getElementById('time').textContent = `${minutes}:${seconds}`;
        timeLeft--;
    }, 1000);
}

function startQuiz() {
    buildQuiz();
    document.getElementById('start').style.display = 'none';
    document.getElementById('submit').style.display = 'inline-block';
    document.getElementById('reset').style.display = 'inline-block';
    startTimer();
}

function resetQuiz() {
    clearInterval(timer);
    timeLeft = 600; // Reset timer
    document.getElementById('start').style.display = 'inline-block';
    document.getElementById('submit').style.display = 'none';
    document.getElementById('reset').style.display = 'none';
    document.getElementById('quiz').innerHTML = '';
    document.getElementById('results').innerHTML = '';
    document.getElementById('time').textContent = '00:00';
    updateProgress(0);
}

function updateProgress(currentQuestionIndex) {
    const progressBar = document.getElementById('progress-bar');
    const totalQuestions = quizData.length;
    const percentage = ((currentQuestionIndex + 1) / totalQuestions) * 100;
    progressBar.style.width = percentage + '%';
    progressBar.textContent = `${Math.round(percentage)}% Complete`;
}

document.getElementById('start').addEventListener('click', () => {
    startQuiz();
    let currentQuestionIndex = 0;

    const answerContainers = document.querySelectorAll('.answers');
    document.addEventListener('change', () => {
        currentQuestionIndex = Array.from(answerContainers).findIndex(container => 
            container.querySelector('input[type="radio"]:checked')
        );
        updateProgress(currentQuestionIndex);
    });
});

document.getElementById('submit').addEventListener('click', showResults);
document.getElementById('reset').addEventListener('click', resetQuiz);
