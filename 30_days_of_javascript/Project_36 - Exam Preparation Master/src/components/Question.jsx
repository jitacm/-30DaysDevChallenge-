// import React, { useEffect, useState } from 'react';
// import { useNavigate, useLocation } from 'react-router-dom';
// import QuizResult from './result';

// const Question = () => {
//     const [quizData, setQuizData] = useState([]); // State to store fetched quiz data
//     const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0); // State to manage the current question index
//     const [selectedOption, setSelectedOption] = useState(""); // State to manage the selected option
//     const [correctCount, setCorrectCount] = useState(0); // State to count correct answers
//     const [incorrectCount, setIncorrectCount] = useState(0); // State to count incorrect answers
//     const [notAttemptedCount, setNotAttemptedCount] = useState(0); // State to count not-attempted questions
//     const [timer, setTimer] = useState(600); // State to manage the timer (10 minutes in seconds)
//     const [result, setResult] = useState(null); // State to store the quiz result
//     const [updateComplete, setUpdateComplete] = useState(false); // State to track quiz data update completion
//     const [isDataFetched, setIsDataFetched] = useState(false); // State to track if data is fetched
//     const navigate = useNavigate(); // Use the useNavigate hook
//     const [queAttempt, setQueAttempt] = useState(Array(10).fill(1));
//     const location = useLocation(); // Use the useLocation hook to get the origin page
//     const { onTryAgain, username, subject, difficulty } = location.state || {};

//     const updateIndex = (index, newValue) => {
//         const newQueAttempt = [...queAttempt]; // Create a copy of the current array
//         newQueAttempt[index] = newValue; // Update the specific index with the new value
//         setQueAttempt(newQueAttempt); // Update the state with the modified array
//     };

//     useEffect(() => {
//         fetchQuestions(); // Fetch quiz questions when the component mounts
//     }, []);

//     useEffect(() => {
//         let timerInterval;
//         if (isDataFetched) {
//             timerInterval = setInterval(() => {
//                 setTimer((prevTimer) => {
//                     if (prevTimer === 0) {
//                         clearInterval(timerInterval);
//                         handleQuizSubmit();
//                     }
//                     return prevTimer - 1;
//                 });
//             }, 1000);
//         }
//         return () => clearInterval(timerInterval);
//     }, [isDataFetched]);

//     useEffect(() => {
//         console.log(quizData); // This will log the updated quizData
//         console.log("array containing values :", queAttempt);
//         console.log("correct count", correctCount);
//         console.log("incorrect count", incorrectCount);
//         console.log("not attempted count", notAttemptedCount);
//         console.log(".");
//         console.log("Done with loading :", currentQuestionIndex + 1);
//     }, [quizData]);

//     const fetchQuestions = async () => {
//         try {
//             const { origin, difficulty } = location.state || {};
//             let url = '';

//             if (origin === 'maths-quiz') {
//                 if (difficulty === 'easy') {
//                     url = `https://opentdb.com/api.php?amount=10&category=19&difficulty=easy`;
//                 } else if (difficulty === 'medium') {
//                     url = `https://opentdb.com/api.php?amount=10&category=19&difficulty=medium`;
//                 } else {
//                     url = `https://opentdb.com/api.php?amount=10&category=19&difficulty=hard`;
//                 }
//             } else if (origin === 'science-quiz') {
//                 if (difficulty === 'easy') {
//                     url = `https://opentdb.com/api.php?amount=10&category=17&difficulty=easy`;
//                 } else if (difficulty === 'medium') {
//                     url = `https://opentdb.com/api.php?amount=10&category=17&difficulty=medium`;
//                 } else {
//                     url = `https://opentdb.com/api.php?amount=10&category=17&difficulty=hard`;
//                 }
//             } else {
//                 url = `https://opentdb.com/api.php?amount=10`;
//             }

//             console.log("fetching url", url);

//             const response = await fetch(url);
//             const data = await response.json();
//             const formattedData = data.results.map((item) => ({
//                 ...item,
//                 choices: [...item.incorrect_answers, item.correct_answer].sort(),
//                 selected: null,
//             }));
//             setQuizData(formattedData); // Set the fetched quiz data in state
//             setIsDataFetched(true); // Set data fetched state to true
//         } catch (error) {
//             console.error('Error fetching quiz questions:', error);
//         }
//     };

//     // Function to handle option selection
//     const handleOptionChange = (e) => {
//         setSelectedOption(e.target.value);
//         console.log(`Question ${currentQuestionIndex + 1}: Selected option: ${e.target.value}`); // Log the selected option
//     };

//     const handleCurrentQuestion = () => {
//         const currentQuestion = quizData[currentQuestionIndex];

//         if (!currentQuestion.selected) {
//             // If the question was not previously attempted
//             if (!selectedOption) {
//                 if (queAttempt[currentQuestionIndex] === 1) {
//                     setNotAttemptedCount((prevCount) => prevCount + 1); // Increment not-attempted count if no option is selected
//                     updateIndex(currentQuestionIndex, 2);
//                 }
//             } else if (selectedOption === currentQuestion.correct_answer) {
//                 // If the selected option is correct
//                 setCorrectCount((prevCount) => prevCount + 1); // Increment correct count
//                 if (queAttempt[currentQuestionIndex] === 2) {
//                     setNotAttemptedCount((prevCount) => prevCount - 1);
//                     updateIndex(currentQuestionIndex, 1);
//                 }
//             } else {
//                 // If the selected option is incorrect
//                 setIncorrectCount((prevCount) => prevCount + 1); // Increment incorrect count
//                 if (queAttempt[currentQuestionIndex] === 2) {
//                     setNotAttemptedCount((prevCount) => prevCount - 1);
//                     updateIndex(currentQuestionIndex, 1);
//                 }
//             }
//         } else {
//             // If the question was previously attempted
//             if (!selectedOption) {
//                 // If the question remains unattempted
//                 // Do nothing
//             } else if (selectedOption === currentQuestion.correct_answer) {
//                 // If the new response is correct
//                 if (currentQuestion.selected === currentQuestion.correct_answer) {
//                     // If the previous response was also correct
//                     // Do nothing
//                 } else {
//                     // If the previous response was incorrect
//                     setCorrectCount((prevCount) => prevCount + 1); // Increment correct count
//                     setIncorrectCount((prevCount) => prevCount - 1); // Decrement incorrect count
//                 }
//             } else {
//                 // If the new response is incorrect
//                 if (currentQuestion.selected === currentQuestion.correct_answer) {
//                     // If the previous response was correct
//                     setCorrectCount((prevCount) => prevCount - 1); // Decrement correct count
//                     setIncorrectCount((prevCount) => prevCount + 1); // Increment incorrect count
//                 } else {
//                     // If the previous response was also incorrect
//                     // Do nothing
//                 }
//             }
//         }

//         // Update quiz data with the selected option
//         const updatedQuizData = quizData.map((question, index) =>
//             index === currentQuestionIndex ? { ...question, selected: selectedOption } : question
//         );

//         // Update states
//         setQuizData(updatedQuizData);
//         setSelectedOption(""); // Clear the selected option
//     };

//     // Function to go to the previous question
//     const handlePrevQuestion = async () => {
//         await handleCurrentQuestion();
//         console.log("Done with :", currentQuestionIndex + 1);
//         setCurrentQuestionIndex((prevIndex) => prevIndex - 1); // Go to the previous question
//         console.log("current question index : ", currentQuestionIndex + " and Question no." + (currentQuestionIndex + 1));
//         console.log("Value at index : ", queAttempt[currentQuestionIndex]);
//     };

//     // Function to go to the next question
//     const handleNextQuestion = async () => {
//         await handleCurrentQuestion();
//         console.log("Done with :", currentQuestionIndex + 1);
//         setCurrentQuestionIndex((prevIndex) => prevIndex + 1); // Go to the next question
//         console.log("current question index : ", currentQuestionIndex + " and Question no." + (currentQuestionIndex + 1));
//         console.log("Value at index : ", queAttempt[currentQuestionIndex]);
//     };

//     // Function to submit the quiz
//     const handleQuizSubmit = () => {
//         if (!selectedOption) {
//             if (queAttempt[currentQuestionIndex] === 1) {
//                 setNotAttemptedCount((prevCount) => prevCount + 1); // Increment not-attempted count if no option is selected
//                 updateIndex(currentQuestionIndex, 2);
//             }
//         } else if (selectedOption === quizData[currentQuestionIndex].correct_answer) {
//             setCorrectCount((prevCount) => prevCount + 1); // Increment correct count if selected option is correct
//         } else {
//             setIncorrectCount((prevCount) => prevCount + 1); // Increment incorrect count if selected option is incorrect
//         }

//         const updatedQuizData = quizData.map((question, index) =>
//             index === currentQuestionIndex ? { ...question, selected: selectedOption } : question
//         );

//         setQuizData(updatedQuizData);
//         setUpdateComplete(true); // Mark update as complete
//         setSelectedOption(""); // Clear the selected option
//     };

//     useEffect(() => {
//         if (updateComplete) {
//             setResult({ correct: correctCount, incorrect: incorrectCount, notAttempted: notAttemptedCount });
//             setUpdateComplete(false); // Reset update completion state
//         }
//     }, [updateComplete, correctCount, incorrectCount, notAttemptedCount]);

//     useEffect(() => {
//         if (result) {
//             setTimer(0); // Stop the timer
//             console.log("Quiz Submitted", {
//                 correct: correctCount,
//                 incorrect: incorrectCount,
//                 notAttempted: notAttemptedCount,
//                 questions: quizData.map((q, index) => ({
//                     question: q.question,
//                     selected: q.selected,
//                     correct: q.correct_answer
//                 }))
//             });
//             console.log(result.correct);
//             console.log(result.incorrect);
//             console.log(result.notAttempted);
//             navigate('/result', { state: { correct: result.correct, incorrect: result.incorrect, notAttempted: result.notAttempted, questions: quizData, tryKey: onTryAgain, username: username, subject: subject, level: difficulty } });
//         }
//     }, [result, navigate, quizData]);

//     // Get the current question from quizData
//     const currentQuestion = quizData[currentQuestionIndex];

//     return (
//         <>
//             {result ? (
//                 <QuizResult correct={result.correct} incorrect={result.incorrect} notAttempted={result.notAttempted} questions={quizData} />
//             ) : (
//                 <div className='mt-8 min-h-screen min-w-screen flex-grow flex text-left items-center justify-center bg-gradient-to-b from-blue-100 to-blue-500'>
//                     <style>
//                         {`
//                             #root{
//                                 margin: 0;
//                                 padding: 0;
//                             }

//                             body {
//                                 overflow: hidden;
//                             }
//                         `}
//                     </style>
//                     <div className="w-full max-w-[40rem] bg-[#2b3353] p-8 rounded-lg shadow-lg" style={{ textAlign: 'left' }}>
//                         <section className="bg-purple-800 text-white p-4 rounded-lg mb-4 flex justify-between">
//                             <h3 className="text-gray-200">
//                                 Question {currentQuestionIndex + 1}/{quizData.length}
//                             </h3>
//                             <span className='flex gap-1'>
//                                 <lord-icon
//                                     src="https://cdn.lordicon.com/kgdqzapd.json"
//                                     trigger="hover"
//                                     style={{ width: '25px', height: '30px' }}>
//                                 </lord-icon>
//                                 <h5 className="text-gray-200">
//                                     {Math.floor(timer / 60)}:{timer % 60 < 10 ? `0${timer % 60}` : timer % 60}
//                                 </h5>
//                             </span>
//                         </section>
//                         {currentQuestion ? (
//                             <section className="grid grid-cols-2 gap-4">
//                                 <div className="col-span-2">
//                                     <p className="text-white text-xl" dangerouslySetInnerHTML={{ __html: currentQuestion.question }}></p>
//                                 </div>
//                                 {currentQuestion.choices.map((option, index) => (
//                                     <div key={index}>
//                                         <label className={`block p-2 rounded-lg cursor-pointer ${selectedOption === option ? 'bg-purple-600 text-white' : 'bg-gray-200 text-gray-800 hover:bg-purple-600 hover:text-white'}`}>
//                                             <input
//                                                 type="radio"
//                                                 name="answer"
//                                                 value={option}
//                                                 onChange={handleOptionChange}
//                                                 checked={selectedOption === option}
//                                                 className="mr-2 cursor-pointer"
//                                             />
//                                             {option}
//                                         </label>
//                                     </div>
//                                 ))}
//                             </section>
//                         ) : (
//                             <div className="text-gray-800">
//                                 <p>Loading...</p>
//                             </div>
//                         )}

//                         <div className="flex justify-between mt-4" style={{ position: 'fixed', bottom: '20px', width: 'calc(100% - 2rem)', left: '1rem' }}>
//                             <div>
//                                 {currentQuestionIndex > 0 && (
//                                     <button
//                                         className="bg-purple-600 text-white px-4 py-2 rounded-lg"
//                                         onClick={handlePrevQuestion}
//                                     >
//                                         Prev
//                                     </button>
//                                 )}
//                             </div>
//                             <div>
//                                 {currentQuestionIndex < quizData.length - 1 ? (
//                                     <button
//                                         className="bg-purple-600 text-white px-4 py-2 rounded-lg"
//                                         onClick={handleNextQuestion}
//                                     >
//                                         Next
//                                     </button>
//                                 ) : (
//                                     <button
//                                         className="bg-purple-600 text-white px-4 py-2 rounded-lg"
//                                         onClick={handleQuizSubmit}
//                                     >
//                                         Submit
//                                     </button>
//                                 )}
//                             </div>
//                         </div>
//                     </div>
//                 </div>
//             )}
//         </>
//     );
// };

// export default Question;
















import React, { useEffect, useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import QuizResult from './result';

const Question = () => {
    const [quizData, setQuizData] = useState([]); // State to store fetched quiz data
    const [currentQuestionIndex, setCurrentQuestionIndex] = useState(0); // State to manage the current question index
    const [selectedOption, setSelectedOption] = useState(""); // State to manage the selected option
    const [correctCount, setCorrectCount] = useState(0); // State to count correct answers
    const [incorrectCount, setIncorrectCount] = useState(0); // State to count incorrect answers
    const [notAttemptedCount, setNotAttemptedCount] = useState(0); // State to count not-attempted questions
    const [timer, setTimer] = useState(600); // State to manage the timer (10 minutes in seconds)
    const [result, setResult] = useState(null); // State to store the quiz result
    const [updateComplete, setUpdateComplete] = useState(false); // State to track quiz data update completion
    const [isDataFetched, setIsDataFetched] = useState(false); // State to track if data is fetched
    const navigate = useNavigate(); // Use the useNavigate hook
    const [queAttempt, setQueAttempt] = useState(Array(10).fill(1));
    const location = useLocation(); // Use the useLocation hook to get the origin page
    const { onTryAgain, username, subject, difficulty } = location.state || {};
    const [nextstate, setnextstate] = useState(false)
    const [prevstate, setprevstate] = useState(false)

    const updateIndex = (index, newValue) => {
        const newQueAttempt = [...queAttempt]; // Create a copy of the current array
        newQueAttempt[index] = newValue; // Update the specific index with the new value
        setQueAttempt(newQueAttempt); // Update the state with the modified array
    };

    useEffect(() => {
        fetchQuestions(); // Fetch quiz questions when the component mounts
    }, []);

    useEffect(() => {
        let timerInterval;
        if (isDataFetched) {
            timerInterval = setInterval(() => {
                setTimer((prevTimer) => {
                    if (prevTimer === 0) {
                        clearInterval(timerInterval);
                        handleQuizSubmit();
                    }
                    return prevTimer - 1;
                });
            }, 1000);
        }
        return () => clearInterval(timerInterval);
    }, [isDataFetched]);

    useEffect(() => {
        console.log(quizData); // This will log the updated quizData
        console.log("array containing values :", queAttempt);
        console.log("correct count", correctCount);
        console.log("incorrect count", incorrectCount);
        console.log("not attempted count", notAttemptedCount);
        console.log(".");
        console.log("Done with loading :", currentQuestionIndex + 1);
    }, [quizData]);

    const fetchQuestions = async () => {
        try {
            const { origin, difficulty } = location.state || {};
            let url = '';

            if (origin === 'maths-quiz') {
                if (difficulty === 'easy') {
                    url = `https://opentdb.com/api.php?amount=10&category=19&difficulty=easy`;
                } else if (difficulty === 'medium') {
                    url = `https://opentdb.com/api.php?amount=10&category=19&difficulty=medium`;
                } else {
                    url = `https://opentdb.com/api.php?amount=10&category=19&difficulty=hard`;
                }
            } else if (origin === 'science-quiz') {
                if (difficulty === 'easy') {
                    url = `https://opentdb.com/api.php?amount=10&category=17&difficulty=easy`;
                } else if (difficulty === 'medium') {
                    url = `https://opentdb.com/api.php?amount=10&category=17&difficulty=medium`;
                } else {
                    url = `https://opentdb.com/api.php?amount=10&category=17&difficulty=hard`;
                }
            } else {
                url = `https://opentdb.com/api.php?amount=10`;
            }

            console.log("fetching url", url);

            const response = await fetch(url);
            const data = await response.json();
            const formattedData = data.results.map((item) => ({
                ...item,
                choices: [...item.incorrect_answers, item.correct_answer].sort(),
                selected: null,
            }));
            setQuizData(formattedData); // Set the fetched quiz data in state
            setIsDataFetched(true); // Set data fetched state to true
        } catch (error) {
            console.error('Error fetching quiz questions:', error);
        }
    };

    // Function to handle option selection
    const handleOptionChange = (e) => {
        setSelectedOption(e.target.value);
        console.log(`Question ${currentQuestionIndex + 1}: Selected option: ${e.target.value}`); // Log the selected option
    };

    const handleCurrentQuestion = () => {
        const currentQuestion = quizData[currentQuestionIndex];

        if (!currentQuestion.selected) {
            // If the question was not previously attempted
            if (!selectedOption) {
                if (queAttempt[currentQuestionIndex] === 1) {
                    setNotAttemptedCount((prevCount) => prevCount + 1); // Increment not-attempted count if no option is selected
                    updateIndex(currentQuestionIndex, 2);
                }
            } else if (selectedOption === currentQuestion.correct_answer) {
                // If the selected option is correct
                setCorrectCount((prevCount) => prevCount + 1); // Increment correct count
                if (queAttempt[currentQuestionIndex] === 2) {
                    setNotAttemptedCount((prevCount) => prevCount - 1);
                    updateIndex(currentQuestionIndex, 1);
                }
            } else {
                // If the selected option is incorrect
                setIncorrectCount((prevCount) => prevCount + 1); // Increment incorrect count
                if (queAttempt[currentQuestionIndex] === 2) {
                    setNotAttemptedCount((prevCount) => prevCount - 1);
                    updateIndex(currentQuestionIndex, 1);
                }
            }
        } else {
            // If the question was previously attempted
            if (!selectedOption) {
                // If the question remains unattempted
                // Do nothing
            } else if (selectedOption === currentQuestion.correct_answer) {
                // If the new response is correct
                if (currentQuestion.selected === currentQuestion.correct_answer) {
                    // If the previous response was also correct
                    // Do nothing
                } else {
                    // If the previous response was incorrect
                    setCorrectCount((prevCount) => prevCount + 1); // Increment correct count
                    setIncorrectCount((prevCount) => prevCount - 1); // Decrement incorrect count
                }
            } else {
                // If the new response is incorrect
                if (currentQuestion.selected === currentQuestion.correct_answer) {
                    // If the previous response was correct
                    setCorrectCount((prevCount) => prevCount - 1); // Decrement correct count
                    setIncorrectCount((prevCount) => prevCount + 1); // Increment incorrect count
                } else {
                    // If the previous response was also incorrect
                    // Do nothing
                }
            }
        }

        // Update quiz data with the selected option
        const updatedQuizData = quizData.map((question, index) =>
            index === currentQuestionIndex ? { ...question, selected: selectedOption } : question
        );

        // Update states
        setQuizData(updatedQuizData);
        setSelectedOption(""); // Clear the selected option
    };

// Function to go to the previous question
const handlePrevQuestion = async () => {
    await handleCurrentQuestion();
    document.querySelector('.question-container').classList.add('prev');
    setTimeout(() => {
        document.querySelector('.question-container').classList.remove('prev');
        }, 1000); // Adjust the delay to match the transition duration
    setCurrentQuestionIndex((prevIndex) => prevIndex - 1);
    document.querySelector('.question-content').addEventListener('transitionend', () => {
        setCurrentQuestionIndex((prevIndex) => prevIndex - 1);
        // document.querySelector('.question-container').classList.remove('prev');
    }, { once: true });
};

// Function to go to the next question
const handleNextQuestion = async () => {
    await handleCurrentQuestion();
    document.querySelector('.question-container').classList.add('next');
    setTimeout(() => {
        document.querySelector('.question-container').classList.remove('next');
        },1000); // Adjust the delay to match the transition duration
    setCurrentQuestionIndex((prevIndex) => prevIndex + 1);
    document.querySelector('.question-content').addEventListener('transitionend', () => {
        setCurrentQuestionIndex((prevIndex) => prevIndex + 1);
        // document.querySelector('.question-container').classList.remove('next');
    }, { once: true });
};



useEffect(() => {
    if (prevstate) {
        document.querySelector('.question-container').classList.remove('prev');
        setprevstate(false);
    }
}, [prevstate]);

useEffect(() => {
    if (nextstate) {
        document.querySelector('.question-container').classList.remove('next');
        setnextstate(false);
    }
}, [nextstate]);



    // Function to submit the quiz
    const handleQuizSubmit = () => {
        if (!selectedOption) {
            if (queAttempt[currentQuestionIndex] === 1) {
                setNotAttemptedCount((prevCount) => prevCount + 1); // Increment not-attempted count if no option is selected
                updateIndex(currentQuestionIndex, 2);
            }
        } else if (selectedOption === quizData[currentQuestionIndex].correct_answer) {
            setCorrectCount((prevCount) => prevCount + 1); // Increment correct count if selected option is correct
        } else {
            setIncorrectCount((prevCount) => prevCount + 1); // Increment incorrect count if selected option is incorrect
        }

        const updatedQuizData = quizData.map((question, index) =>
            index === currentQuestionIndex ? { ...question, selected: selectedOption } : question
        );

        setQuizData(updatedQuizData);
        setUpdateComplete(true); // Mark update as complete
        setSelectedOption(""); // Clear the selected option
    };

    useEffect(() => {
        if (updateComplete) {
            setResult({ correct: correctCount, incorrect: incorrectCount, notAttempted: notAttemptedCount });
            setUpdateComplete(false); // Reset update completion state
        }
    }, [updateComplete, correctCount, incorrectCount, notAttemptedCount]);

    useEffect(() => {
        if (result) {
            setTimer(0); // Stop the timer
            console.log("Quiz Submitted", {
                correct: correctCount,
                incorrect: incorrectCount,
                notAttempted: notAttemptedCount,
                questions: quizData.map((q, index) => ({
                    question: q.question,
                    selected: q.selected,
                    correct: q.correct_answer
                }))
            });
            console.log(result.correct);
            console.log(result.incorrect);
            console.log(result.notAttempted);
            navigate('/result', { state: { correct: result.correct, incorrect: result.incorrect, notAttempted: result.notAttempted, questions: quizData, tryKey: onTryAgain, username: username, subject: subject, level: difficulty } });
        }
    }, [result, navigate, quizData]);

    // Get the current question from quizData
    const currentQuestion = quizData[currentQuestionIndex];
    // n = 2 ;

    return (
        <>
            {result ? (
                <QuizResult correct={result.correct} incorrect={result.incorrect} notAttempted={result.notAttempted} questions={quizData} />
            ) : (
                <div className='mt-8 min-h-screen min-w-screen flex-grow flex text-left items-center justify-center bg-gradient-to-b from-blue-100 to-blue-500'>
                    <style>
                        {`
                            #root{
                                margin: 0;
                                padding: 0;
                            }

                            body {
                                overflow: hidden;
                            }

                            .question-container {
                                position: relative;
                                width: 100%;
                                height: 100%;
                            }

                            .next {
                                transform: translateX(-110%); /* Move to the left */
                                transition: transform 0.1s ease-in-out;
                            }

                            .prev {
                                transform: translateX(110%); /* Move to the right */
                                transition: transform 0.1s ease-in-out;
                            }

                            /* Add this to ensure smooth transition */
                            .question-content {
                                
                                position: absolute;
                                width: 100%;
                                height: 100%;
                                transition: transform 0.1s ease-in-out;
                            }

                        `}
                    </style>
                    <div className="w-full max-w-[50rem] bg-[#2b3353] p-8 rounded-lg overflow-hidden shadow-lg" style={{ textAlign: 'left' , minHeight: '25rem' }}>
                        <section className="bg-purple-800 text-white p-4 rounded-lg mb-4 flex justify-between">
                            <h3 className="text-gray-200">
                                Question {currentQuestionIndex + 1}/{quizData.length}
                            </h3>
                            <span className='flex gap-1'>
                                <lord-icon
                                    src="https://cdn.lordicon.com/kgdqzapd.json"
                                    trigger="hover"
                                    style={{ width: '25px', height: '30px' }}>
                                </lord-icon>
                                <h5 className="text-gray-200">
                                    {Math.floor(timer / 60)}:{timer % 60 < 10 ? `0${timer % 60}` : timer % 60}
                                </h5>
                            </span>
                        </section>
                        <div className="question-container">
                            {/* Current question section */}
                            <div className={`question-content ${prevstate ? 'prev' : nextstate ? 'next' : ''}`}>
                            {currentQuestion ? (
                                <section className="grid grid-cols-2 gap-4 ">
                                    {/* Question content */}
                                    <div className="col-span-2">
                                        <p className="text-white text-xl" dangerouslySetInnerHTML={{ __html: currentQuestion.question }}></p>
                                    </div>
                                    {/* Answer options */}
                                    {currentQuestion.choices.map((option, index) => (
                                        <div key={index}>
                                            <label className={`block p-2 rounded-lg cursor-pointer ${selectedOption === option ? 'bg-purple-600 text-white' : 'bg-gray-200 text-gray-800 hover:bg-purple-600 hover:text-white'}`}>
                                                <input
                                                    type="radio"
                                                    name="answer"
                                                    value={option}
                                                    onChange={handleOptionChange}
                                                    checked={selectedOption === option}
                                                    className="mr-2 cursor-pointer"
                                                />
                                                {option}
                                            </label>
                                        </div>
                                    ))}
                                </section>
                            ) : (
                                <div className="text-gray-800">
                                    <p>Loading...</p>
                                </div>
                            )}
                            </div>
                        </div>



                        <div className="flex justify-between mt-4" style={{ position: 'fixed', bottom: '20px', width: 'calc(100% - 2rem)', left: '1rem' }}>
                            <div>
                                {currentQuestionIndex > 0 && (
                                    <button
                                        className="bg-purple-600 text-white px-4 py-2 rounded-lg"
                                        onClick={handlePrevQuestion}
                                    >
                                        Prev
                                    </button>
                                )}
                            </div>
                            <div>
                                {currentQuestionIndex < quizData.length - 1 ? (
                                    <button
                                        className="bg-purple-600 text-white px-4 py-2 rounded-lg"
                                        onClick={handleNextQuestion}
                                    >
                                        Next
                                    </button>
                                ) : (
                                    <button
                                        className="bg-purple-600 text-white px-4 py-2 rounded-lg"
                                        onClick={handleQuizSubmit}
                                    >
                                        Submit
                                    </button>
                                )}
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </>
    );
};

export default Question;


