// quiz app
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
    }
];

function buildQuiz() {
    const quizContainer = document.getElementById('quiz');
    const output = [];

    quizData.forEach((currentQuestion, questionNumber) => {
        const answers = [];
        for (letter in currentQuestion.answers) {
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

document.getElementById('submit').addEventListener('click', showResults);

buildQuiz();
