// script.js
const socket = new WebSocket('ws://localhost:3000'); // Replace with your WebSocket server URL

socket.onopen = function(event) {
    console.log('WebSocket connected');
};

socket.onmessage = function(event) {
    const message = event.data;
    displayMessage(message);
};

function sendMessage() {
    const messageInput = document.getElementById('message-input');
    const message = messageInput.value.trim();
    if (message !== '') {
        socket.send(message);
        messageInput.value = '';
    }
}

function displayMessage(message) {
    const chatMessages = document.getElementById('chat-messages');
    const messageElement = document.createElement('div');
    messageElement.textContent = message;
    chatMessages.appendChild(messageElement);
    chatMessages.scrollTop = chatMessages.scrollHeight; // Scroll to bottom
}
