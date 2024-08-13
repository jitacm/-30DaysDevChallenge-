# Broadcasting Chat Server

## Project Overview

This project implements a Broadcasting Chat Server using Java. The server allows multiple clients to connect and broadcast messages to all connected clients in real-time.

## Key Features

- **Multi-Client Support:** Handles multiple client connections simultaneously using multi-threading.
- **Real-Time Messaging:** Broadcasts messages instantly to all connected clients.
- **Robust Error Handling:** Ensures stability and graceful handling of unexpected issues.
- **Simple Console-Based Interface:** Provides a basic command-line interface for both server and clients.

## Components

### 1. ChatServer.java

The server application listens for incoming client connections and creates a new thread (`ClientHandler`) for each client. It broadcasts messages received from any client to all other connected clients.

### 2. ChatClient.java

The client application connects to the server and allows users to send messages through the console. It also listens for and displays incoming messages from the server.

## Getting Started

### Prerequisites

- **Java Development Kit (JDK)**: Ensure you have JDK 8 or higher installed on your machine. You can verify this by running `javac -version` in your terminal.

### Installation

1. **Clone the Repository**

   ```bash
   git clone https://github.com/nikharepriyant/30DaysDevChallenge.git
   ```

2. **Navigate to the Project Directory**

   ```bash
   cd 30DaysDevChallenge/30_days_of_java
   ```

3. **Create Java Files**

   Create the following Java files in the directory:

   - `ChatServer.java`
   - `ChatClient.java`

   Copy the respective code from the `ChatServer.java` and `ChatClient.java` provided in the project description.

### Compilation

Compile the Java files using the following commands:

```bash
javac ChatServer.java
javac ChatClient.java
```

### Running the Project

1. **Start the Server**

   Open a terminal and run:

   ```bash
   java ChatServer
   ```

   The server will start and wait for client connections.

2. **Start the Clients**

   Open one or more terminals and run:

   ```bash
   java ChatClient
   ```

   Each client will connect to the server and allow you to send and receive messages.

### Usage

- **Sending Messages:** Type a message in the client console and press Enter. The message will be broadcasted to all connected clients.
- **Receiving Messages:** Incoming messages from other clients will be displayed in the client console.

### Example

**Client 1:**

```
Connected to the chat server
Hello everyone!
Server: Hello everyone!
```

**Client 2:**

```
Connected to the chat server
Server: Hello everyone!
Hi there!
Server: Hi there!
```

**Client 3:**

```
Connected to the chat server
Server: Hello everyone!
Server: Hi there!
Good day!
Server: Good day!
```

### Contributing

Feel free to fork the repository and submit pull requests with improvements or bug fixes. Ensure that your contributions are well-documented and tested.

### License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

### Acknowledgments

- Java Networking and Multi-threading tutorials that helped in implementing this chat server.