# MULTITHREADED SERVER
This example explains how to implement a basic multithreaded server:
- In order to do homework, _'client'_ (student) will send request by typing 'start' to the input command line. The _'server'_ (school) will receive the request and response with a random generated homework. The student then will need to finish the homework and send it back to the school to get grades.
- _'server'_ (school) will serve each student request based on each thread, so that it won't block all the students who are trying to access the server the same time while some other students are doing their homework.

**NOTES:**
This implementation is written to used for 'localhost' only.

## Requirements
- Java version: 8

## Resources
- Network Socket: [Click Here](https://en.wikipedia.org/wiki/Network_socket)
- Serializable Objects: [Click Here](https://docs.oracle.com/javase/tutorial/jndi/objects/serial.html)
