# Real-Time Chat Application 
 Issues#241

A simple real-time chat application built using HTML, CSS, and JavaScript. Users can join chat rooms, send messages, and view the message history.

__Features:__

Real-time Messaging: Send and receive messages instantly.
Multiple Chat Rooms: Users can join chat rooms to discuss various topics.
User-friendly Interface: Clean and responsive design for an enjoyable chat experience.
Message History: View the history of messages in the chat room.
### Technologies Used:

__Frontend:__

_HTML_: Structure of the application.

_CSS:_ Styling and layout.

_JavaScript:_ Client-side scripting for real-time interaction.

## Getting Started
__Prerequisites__:

- Basic knowledge of HTML, CSS, and JavaScript.
- A modern web browser (Google Chrome, Mozilla Firefox, etc.).

## File Structure

```http
  realtime-chat-app/
├── index.html
├── styles.css
└── app.js

```

## JavaScript Functionality

The JavaScript file is where the real-time functionality is implemented. Below is a detailed breakdown of how it works:

1. _WebSocket Connection_
A WebSocket connection is established between the client and server. This connection allows for real-time communication.

```http
const socket = new WebSocket('ws://localhost:8080');
```

2. _Event Listeners_
Event listeners are set up to handle user interactions and incoming messages.

```http

document.getElementById('sendBtn').addEventListener('click', sendMessage);


document.getElementById('messageInput').addEventListener('keypress', function (e) {
    if (e.key === 'Enter') {
        sendMessage();
    }
});

// Listen for incoming messages
socket.addEventListener('message', function (event) {
    displayMessage(event.data);
});

```

3. _Sending Messages_
When a user sends a message, it is sent to the server using the WebSocket connection.

```http
function sendMessage() {
    const message = document.getElementById('messageInput').value;
    if (message.trim()) {
        socket.send(message);
        document.getElementById('messageInput').value = '';
    }
}

```

4. _Receiving Messages_
When a message is received from the server, it is displayed in the chat area.

```http
```http
function sendMessage() {
    const message = document.getElementById('messageInput').value;
    if (message.trim()) {
        socket.send(message);
        document.getElementById('messageInput').value = '';
    }
}

```

## Future Enhancements
- __User Authentication__: Implement user login and authentication.
- __Private Messaging__: Allow users to send private messages.
- __Message Formatting__: Add support for rich text formatting (bold, italic, etc.).

## Conclusion
This real-time chat application demonstrates how you can build a fully functional chat system using only HTML, CSS, and JavaScript. The use of WebSockets for real-time communication is key to ensuring messages are instantly shared between users.

_Feel free to contribute to this project by submitting pull requests or opening issues for any bugs or feature requests._
