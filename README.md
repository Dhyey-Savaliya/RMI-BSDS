# RMI Key-Value Store

This is a simple implementation of a remote key-value store using Java RMI (Remote Method Invocation). It consists of two main components: a server and a client. The server allows clients to remotely perform operations such as putting, getting, and deleting key-value pairs. The client provides a user interface to interact with the server.

--- 

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Usage](#usage)
- [Server](#server)
- [Client](#client)

---

## Features

- **PUT**: Clients can remotely add key-value pairs to the server's store.
- **GET**: Clients can remotely retrieve the value associated with a specific key.
- **DELETE**: Clients can remotely delete a key-value pair from the server's store.
- **Timestamped Logging**: Both the server and client log their activities with timestamped messages.

---

## Prerequisites

Before you can use this project, you need to ensure you have the following installed on your system:

- Java Development Kit (JDK 8 or above)
- Git (if you want to clone this repository)

---
## Usage

### Server

1. Clone this repository (if not already done):

   git clone https://github.com/yourusername/rmi-key-value-store.git

2. Compile the server:

   javac Server.java

3. Run the server with a specified port (e.g., 1099):

   java Server <PORT_NUMBER>

The server is now running and listening for client connections on the specified port.

### Client

1. Compile the client:

    javac Client.java
    javac Client2.java

2. Run the client, specifying the IP address and port where the server is running:

    java Client <HOST_IP> <PORT_NUMBER>
    java Client2 <HOST_IP> <PORT_NUMBER>

3. You can interact with the client by following the on-screen instructions to PUT, GET, DELETE, or exit the client.

