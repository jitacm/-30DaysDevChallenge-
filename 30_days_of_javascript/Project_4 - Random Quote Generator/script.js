const localQuotes = [
  {
    text: "Life is what happens when you’re busy making other plans.",
    author: "John Lennon",
  },
  { text: "Get busy living or get busy dying.", author: "Stephen King" },
  {
    text: "Whether you think you can or you think you can’t, you’re right.",
    author: "Henry Ford",
  },
  // ... Additional quotes
];

async function fetchQuotesFromAPI() {
  try {
    const response = await fetch("https://api.quotable.io/random");
    if (!response.ok) {
      throw new Error("Network response was not ok");
    }
    const data = await response.json();
    return [{ text: data.content, author: data.author }];
  } catch (error) {
    console.error("Fetching quotes from API failed:", error);
    return localQuotes;
  }
}

async function getRandomQuote() {
  const quotes = await fetchQuotesFromAPI();
  const index = Math.floor(Math.random() * quotes.length);
  return quotes[index];
}

async function displayQuote() {
  const quote = await getRandomQuote();
  const quoteTextElement = document.getElementById("quoteText");
  const quoteAuthorElement = document.getElementById("quoteAuthor");

  if (quoteTextElement && quoteAuthorElement) {
    quoteTextElement.textContent = `"${quote.text}"`;
    quoteAuthorElement.textContent = `- ${quote.author}`;
  } else {
    console.error("Make sure your HTML elements have the correct IDs.");
  }
}

// This function will run when the DOM is fully loaded
document.addEventListener("DOMContentLoaded", function () {
  const newQuoteButton = document.getElementById("newQuoteButton");

  if (newQuoteButton) {
    newQuoteButton.addEventListener("click", displayQuote);
    // Call displayQuote on initial load to show a quote immediately
    displayQuote();
  } else {
    console.error("No button found with ID `newQuoteButton`.");
  }
});
