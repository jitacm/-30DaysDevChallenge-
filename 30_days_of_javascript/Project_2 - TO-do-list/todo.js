// todo.js

document.addEventListener('DOMContentLoaded', () => {
    const addButton = document.getElementById('addTaskButton');
    const newTaskInput = document.getElementById('newTask');
    const taskList = document.getElementById('taskList');
  
    // Load stored tasks
    const tasks = JSON.parse(localStorage.getItem('tasks')) || [];
    tasks.forEach(task => createTaskItem(task));
  
    addButton.addEventListener('click', () => {
      const taskText = newTaskInput.value.trim();
      if (taskText) {
        const task = { text: taskText, completed: false };
        tasks.push(task);
        createTaskItem(task);
        newTaskInput.value = '';
        saveToLocalStorage();
      }
    });
  
    function createTaskItem(task) {
      const li = document.createElement('li');
      li.className = 'task-item';
      if (task.completed) {
        li.classList.add('completed');
      }
  
      li.innerHTML = `
        <span>${task.text}</span>
        <button class="delete-button">X</button>
      `;
  
      // Toggle completion
      li.addEventListener('click', () => {
        task.completed = !task.completed;
        li.classList.toggle('completed', task.completed);
        saveToLocalStorage();
      });
  
      // Delete task
      li.querySelector('.delete-button').addEventListener('click', () => {
        li.remove();
        tasks.splice(tasks.indexOf(task), 1);
        saveToLocalStorage();
        event.stopPropagation();
      });
  
      taskList.appendChild(li);
    }
  
    function saveToLocalStorage() {
      localStorage.setItem('tasks', JSON.stringify(tasks));
    }
  
  });