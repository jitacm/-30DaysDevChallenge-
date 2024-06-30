// script.js
let notes = [];

function addNote() {
    const noteInput = document.getElementById('note-input');
    const noteText = noteInput.value.trim();
    if (noteText === '') return;

    notes.push(noteText);
    noteInput.value = '';
    displayNotes();
}

function editNote(index) {
    const noteText = prompt('Edit your note:', notes[index]);
    if (noteText === null || noteText.trim() === '') return;
    notes[index] = noteText.trim();
    displayNotes();
}

function deleteNote(index) {
    notes.splice(index, 1);
    displayNotes();
}

function displayNotes() {
    const notesDiv = document.getElementById('notes');
    notesDiv.innerHTML = '';

    notes.forEach((note, index) => {
        const noteCard = document.createElement('div');
        noteCard.classList.add('note-card');

        noteCard.innerHTML = `
            <textarea readonly>${note}</textarea>
            <button onclick="deleteNote(${index})">Delete</button>
            <button onclick="editNote(${index})">Edit</button>
        `;

        notesDiv.appendChild(noteCard);
    });
}
