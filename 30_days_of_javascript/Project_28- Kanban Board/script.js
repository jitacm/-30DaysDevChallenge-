// script.js
let draggedTask;

function allowDrop(event) {
    event.preventDefault();
}

function drag(event) {
    draggedTask = event.target;
}

function drop(event) {
    event.preventDefault();
    const target = event.target;
    if (target.classList.contains('task-list')) {
        target.appendChild(draggedTask);
    }
}
