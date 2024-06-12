const quotes = [
    { text: "Life is what happens when you’re busy making other plans.", author: "John(customer) Lennon" },
    { text: "Get busy living or get busy dying.", author: "Stephen King" },
    { text: "Whether you think you can or you think you can’t, you’re right.", author: "Henry Ford" },
    // ... Additional quotes
  ];
  
  function getRandomQuote() {
    const index = Math.floor(Math.random() * quotes.length);
    return quotes[index];
  }
  
  function displayQuote() {
    const quote = getRandomQuote();
    const quoteTextElement = document.getElementById('quoteText');
    const quoteAuthorElement = document.getElementById('quoteTypoAuthor');
  
    if(quoteTextElement && quoteAuthorElement) {
      quoteTextElement.textContent = `"${quote.text}"`;
      quoteAuthorElement.textContent = `- ${quote.author}`;
    } else {
      console.error('Make sure your HTML elements have the correct IDs.');
    }
  }
  
  // This function will run when the DOM is fully loaded
  document.addEventListener('DOMContentLoaded', function() {
    const newQuoteButton = document.getElementById('newQuoteButton');
  
    if(newQuoteButton) {
      newQuoteButton.addEventListener('click', displayQuote);
      // Call displayQuote on initial load to show a quote immediately
      displayQuote();
    } else {
      console.error('No button found with ID `newQuoteButton`.');
    }
  });