const socket = io();

const chatBox = document.getElementById('chat-box');
const messageInput = document.getElementById('message-input');
const sendButton = document.getElementById('send-button');

// Send message to the server
sendButton.addEventListener('click', sendMessage);
messageInput.addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        sendMessage();
    }
});

function sendMessage() {
    const message = messageInput.value;
    if (message.trim()) {
        socket.emit('chat message', message);
        addMessageToUI(message, 'sent');
        messageInput.value = '';
    }
}

function addMessageToUI(msg, type) {
    const messageElement = document.createElement('div');
    messageElement.textContent = msg;
    messageElement.classList.add(type);
    chatBox.appendChild(messageElement);
    chatBox.scrollTop = chatBox.scrollHeight;
}

// Receive message from the server
socket.on('chat message', (msg) => {
    addMessageToUI(msg, 'received');
});
