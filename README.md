# CLIENT-SERVER MODEL
This repository contains examples for Client-Server Model:
- Basic sockets communication.
- Serialization/Deserialization (using in byte stream).
- Remote Method Invocation (RMI).
- Multicast.

## Explanation
Imagine a **server** is running in a machine. Running essentially means that the **server** goes into listening mode; in other words, cyclically checking if any data has arrived. Now, data arrives through a network channel, right? But, the type of data is defined by the _protocol_ it adheres to. _Protocol_ defines the norm followed in data packing. Some popular _protocols_ are HTTP, FTP, SMTP, and so forth. (It is like a wrapper that determines its citizenship and how the data is to be treated by the **server**.) Recall that the **server** cyclically listens to the **socket** (remember, it is a virtual thing). The **server** may listen to many **sockets**. Each **socket** is uniquely identified by the IP address that helps in isolating the right machine in the network and the port number that determines the **socket** or right endpoint of the machine that **server** specifically listens to.

Different routing schemes:
- Unicast: Message send between two machines in a network.
- Broadcast: Message send to all the machines in the network.
- Unicast: Message send to one or more of the machines in the network.

## Resources
- Client-Server Model: [Click Here](https://en.wikipedia.org/wiki/Client–server_model)
