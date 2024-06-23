### **Description**
This Python script is a web crawler designed to extract email addresses from a target URL. It leverages the requests library to fetch web page content and the BeautifulSoup library to parse HTML. The script uses a breadth-first search approach to navigate through links found on the pages, collecting email addresses along the way.

### **Key features**
**Target URL Input:** Users can specify the initial URL to start the crawling process.
**Breadth-First Search:** The script processes URLs in a breadth-first manner, ensuring a wide exploration of the website.
**Email Extraction:** Utilizes regular expressions to identify and collect email addresses from the fetched web pages.
**Link Resolution:** Handles relative and absolute URLs to ensure all links are correctly processed.
**Duplicate Handling:** Keeps track of processed URLs to avoid redundancy.
**Interrupt Handling:** Gracefully handles user interrupts (Ctrl+C) to stop the script.
**Scalability:** Processes up to 100 URLs by default, with easy modification for larger crawls.

### Required Libraries
_Windows and Linux Installation Commands_

**Windows**
To install the required libraries on Windows, open Command Prompt and run:
> pip install beautifulsoup4 requests lxml

**Linux**
To install the required libraries on Linux, open Terminal and run:
> pip install beautifulsoup4 requests lxml

**Alternative Method**
Open Command Prompt(windows)/Terminal(Linux) and run:
(Make sure you are in the same directory where the requirements.txt is saved before executing the command)
> pip install -r requirements.txt
This method is convenient for managing dependencies in a project and ensures that all required libraries are installed consistently.


**Step-by-step breakdown of how the script works**

**1. Initialize Variables:**

- user_url: The target URL entered by the user.
- urls: A deque initialized with the target URL, used to keep track of URLs to be scraped.
- scraped_urls: A set to keep track of already scraped URLs to avoid duplication.
- emails: A set to store the extracted email addresses.

**Crawl Loop:**

- The script enters a while loop that continues until there are no more URLs to scrape or until it processes 100 URLs.
- It dequeues the next URL to process and adds it to the scraped_urls set.
- It constructs the base URL and path for resolving relative URLs found on the page.

**Fetching and Parsing:**

- The script attempts to fetch the URL content using requests.get(). If the request fails due to an invalid schema or connection error, it skips to the next URL.
- It uses a regular expression to find and collect email addresses in the fetched content.
- It parses the HTML content with BeautifulSoup and finds all anchor tags (<a>).

**Link Handling:**

- For each link found, it resolves relative URLs to absolute URLs.
- It checks if the link has already been processed or is in the queue to be processed. If not, it adds the link to the urls deque.

**Output:**

- The script prints each extracted email address.

**Error Handling:**

- The script handles KeyboardInterrupt to allow the user to stop the script with a keyboard interrupt.

### Screen Shots
![Scraper](https://github.com/jitacm/-30DaysDevChallenge-/assets/114426960/3e3e19a9-cc38-447d-b9fd-f1b02f457245)