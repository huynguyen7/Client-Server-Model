# GENERATE RANDOM NUMBERS
This example explains basic of client-server programming:
- Multiple distributed applications can communicate with each other using _sockets_.
- For JVM A and JVM B to communicate with each other, we assumed that JVM A plays the _'client'_ role and JVM B plays the _'server'_ role. To establish the connection, the main thread in JVM B first creates a _'ServerSocket'_ with a specified address. It then waits for _'client'_ processes to connect to this socket. Also, the _'client'_ role (JVM A) needs its own socket to start the communication with JVM B.

**NOTES:**
This implementation is written to used for 'localhost' only.

## Requirements
- Java version: 8

## Resources
- Network Socket: [Click Here](https://en.wikipedia.org/wiki/Network_socket)
