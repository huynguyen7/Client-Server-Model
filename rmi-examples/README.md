# REMOTE METHOD INVOCATION
This example explains how a thread from a JVM can invoke a method from an object (from another JVM):
- In order to tell a dog to bark (reside in _server_ object), customer (_client_) needs to get a _stub_ object from the registry (created by the server).

**NOTES:**
This implementation is written to used for 'localhost' only.

## Quick-start
- Initialize RMI Registry and attached it to a specific i**port**:

```
$ rmiregistry <port>
```

- Compile all the Java source codes:

```
$ javac *.java
```

- Start the server, the port needs to match with the port we used for RMI Registry:

```
$ java DogCenter
```

- Start the client, the port needs to match with the port we used for RMI Registry:

```
$ java Customer
```

## Resources
- Distributed Object Communication: [Click here](https://en.wikipedia.org/wiki/Distributed_object_communication)
- Java RMI: [Click Here](https://en.wikipedia.org/wiki/Java_remote_method_invocation)
