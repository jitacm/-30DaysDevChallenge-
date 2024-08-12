
# Task Scheduler

Task Scheduler is a simple, user-friendly web application that allows users to create, manage, and prioritize tasks efficiently. This project features a clean and colorful interface, making task management visually appealing and easy to navigate.

## Features

- **Add Tasks:** Users can add new tasks by entering the task name, selecting its priority, and setting a deadline.
- **Task Prioritization:** Tasks can be categorized as Top Priority, Middle Priority, or Low Priority, helping users to organize their tasks based on importance.
- **Task Management:** Tasks are displayed in a card-like format, with the option to delete them as needed.
- **Local Storage:** Tasks are saved in the browser's local storage, ensuring they persist even after the page is refreshed or the browser is closed.
- **Responsive Design:** The application is fully responsive, adapting to different screen sizes for an optimal user experience on both desktop and mobile devices.

## Technologies Used

- **HTML5:** Structure and layout of the application.
- **CSS3:** Styling, animations, and responsive design.
- **JavaScript (ES6+):** Core functionality, including task management, local storage integration, and DOM manipulation.

## Getting Started

### Prerequisites

To run this project locally, you need a modern web browser such as Google Chrome, Firefox, Safari, or Edge.


### Installation
``
**Open the project:**

   Open `index.html` in your preferred web browser.


### Usage

1. **Add a Task:**
   - Enter the task name in the input field.
   - Select the task's priority from the dropdown menu.
   - Optionally, set a deadline for the task.
   - Click the "Add Task" button to add the task to the list.

2. **Manage Tasks:**
   - View all your tasks in the task list, displayed with priority and deadline information.
   - Click the "Delete" button next to a task to remove it from the list.

3. **Persistent Tasks:**
   - Your tasks are saved in the browser's local storage, so they will be available even after refreshing the page or closing the browser.


### Project Structure

```plaintext
task-scheduler/
│
├── index.html        # Main HTML file
├── styles.css        # CSS file for styling the application
└── script.js          # JavaScript file for task management logic