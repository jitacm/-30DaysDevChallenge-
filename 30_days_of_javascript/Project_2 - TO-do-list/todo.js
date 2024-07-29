// todo.js

document.addEventListener('DOMContentLoaded', () => {
    const addButton = document.getElementById('addTaskButton');
    const newTaskInput = document.getElementById('newTask');
    const taskList = document.getElementById('taskList');
  
    // Load stored tasks
    const tasks = JSON.parse(localStorage.getItem('tasks')) || [];
    tasks.forEach(task => createTaskItem(task));
  
    addButton.addEventListener("click", addTask);
    newTaskInput.addEventListener("keypress", (e) => {
      if (e.key === "Enter") {
        addTask();
      }
    });

    function addTask() {
      const taskText = newTaskInput.value.trim();
      if (taskText) {
        const task = { text: taskText, completed: false };
        tasks.push(task);
        createTaskItem(task);
        newTaskInput.value = "";
        saveToLocalStorage();
      }
    }
  
    function createTaskItem(task) {
      const li = document.createElement('li');
      li.className = 'task-item';
      if (task.completed) {
        li.classList.add('completed');
      }
  
      li.innerHTML = `
        <span contenteditable="true">${task.text}</span>
        <button class="delete-button">X</button>
      `;
  
      // Toggle completion
      li.addEventListener('click', () => {
        task.completed = !task.completed;
        li.classList.toggle('completed', task.completed);
        saveToLocalStorage();
      });

      // Edit task
      const taskSpan = li.querySelector("span");
      taskSpan.addEventListener("blur", () => {
        task.text = taskSpan.innerText;
        saveToLocalStorage();
      });

      // Prevent toggle completion on edit
      taskSpan.addEventListener("click", (e) => {
        e.stopPropagation();
      });
  
      // Delete task
      li.querySelector('.delete-button').addEventListener('click', (e) => {
        e.stopPropagation();
        li.remove();
        tasks.splice(tasks.indexOf(task), 1);
        saveToLocalStorage();
      });
  
      taskList.appendChild(li);
    }
  
    function saveToLocalStorage() {
      localStorage.setItem('tasks', JSON.stringify(tasks));
    }
  
  });