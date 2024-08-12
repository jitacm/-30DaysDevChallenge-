// Select DOM elements
const taskInput = document.getElementById('task');
const prioritySelect = document.getElementById('priority');
const deadlineInput = document.getElementById('deadline');
const addTaskButton = document.getElementById('add-task');
const taskList = document.getElementById('task-list');

// Retrieve tasks from local storage or initialize an empty array
let tasks = JSON.parse(localStorage.getItem('tasks')) || [];

// Function to display tasks on the page
function displayTasks() {
	taskList.innerHTML = ''; // Clear existing tasks

	tasks.forEach((task, index) => {
		// Create task container
		const taskItem = document.createElement('div');
		taskItem.classList.add('task');

		// Task details
		const taskDetails = document.createElement('div');
		taskDetails.classList.add('task-details');
		taskDetails.innerHTML = `
			<strong>${task.name}</strong>
			<div class="task-priority">Priority: ${task.priority.charAt(0).toUpperCase() + task.priority.slice(1)}</div>
			<div class="task-deadline">Deadline: ${task.deadline ? task.deadline : 'No deadline'}</div>
		`;

		// Delete button
		const deleteButton = document.createElement('button');
		deleteButton.textContent = 'Delete';
		deleteButton.addEventListener('click', () => {
			deleteTask(index);
		});

		// Append details and button to task container
		taskItem.appendChild(taskDetails);
		taskItem.appendChild(deleteButton);

		// Append task to task list
		taskList.appendChild(taskItem);
	});
}

// Function to add a new task
function addTask() {
	const taskName = taskInput.value.trim();
	const priority = prioritySelect.value;
	const deadline = deadlineInput.value;

	if (taskName === '') {
		alert('Please enter a task name.');
		return;
	}

	const newTask = {
		name: taskName,
		priority: priority,
		deadline: deadline
	};

	tasks.push(newTask);
	saveTasks();
	displayTasks();
	clearForm();
}

// Function to delete a task
function deleteTask(index) {
	tasks.splice(index, 1);
	saveTasks();
	displayTasks();
}

// Function to save tasks to local storage
function saveTasks() {
	localStorage.setItem('tasks', JSON.stringify(tasks));
}

// Function to clear the input form
function clearForm() {
	taskInput.value = '';
	prioritySelect.value = 'top';
	deadlineInput.value = '';
}

// Event listener for the "Add Task" button
addTaskButton.addEventListener('click', addTask);

// Display tasks on page load
document.addEventListener('DOMContentLoaded', displayTasks);
