# ice-fibonacci

Project with ICE and distributed programming and making a fibonacci algorithm

We will use Gradle to create our application projects. You must install Gradle before continuing with this tutorial.

The main design goals of Ice are:

* Provide an object-oriented RPC framework suitable for use in heterogeneous environments.
* Provide a full set of features that support development of realistic distributed applications for a wide variety of domains.
* Avoid unnecessary complexity, making the platform easy to learn and to use.
* Provide an implementation that is efficient in network bandwidth, memory use, and CPU overhead.
* Provide an implementation that has built-in security, making it suitable for use over insecure public networks

# Asignación 📚

1. Modify the client of the helloworld example so that, instead of sending the server a single message from the client, it asks for it by console in a cycle. To this message, the client must prefix the logged username (whoami) and hostname of the client machine with a colon (":").

2. If the message is a positive integer, the server must print in its console the numbers of the Fibonacci(n) series preceded by the username/hostname of the client that sent the message, where n is the received integer, and return the last calculated value. Otherwise, it should simply print the message received and return 0. (fib(1)=1, fib(2)=1)

3. Repeat until the message typed is "exit".



# Configuration 👓

For this demo we're going to use a project with two sub-projects to build the Client and Server applications. The requirements for our sub-projects are the same so we'll do all the setup in the subprojects block of the root project, which applies to all sub-projects. Edit the generated build.gradle file to look like the one below:

```Slice
...

"Class-Path": configurations.runtimeClasspath.resolve().collect { it.toURI() }.join(' ')
...

```

We must also edit the generated settings.gradle to define our sub-projects:

```
rootProject.name = 'printer'
include 'client'
include 'server'
```

​

# Configuration the IP address of the server 🖥️

In order to be able to run our program on remote computers, there are 2 files that we must modify: **config.server and config.client**. This in order to put the IP address of the computer that will be the Server in the **Ice.Default.Host** attribute. If the attribute **Ice.Default.Host=localhost**, the program will run locally.

### server/src/main/resources/config.server ###

```
#
# The server creates one single object adapter with the name
# "Hello". The following line sets the endpoints for this
# adapter.
#
Printer.Endpoints=tcp -p 9099
#
# Only listen on the ZeroTier's LIASOn1 interface by default.
#
#Ice.Default.Host=10.147.19.157
Ice.Default.Host=localhost

```

### client/src/main/resources/config.client ###

```
#
# The client reads this property to create the reference to the
# "hello" object in the server.
#
Printer.Proxy=SimplePrinter:tcp -p 9099
#
# Uncomment to use the WebSocket transports instead.
#
#Hello.Proxy=hello:ws -p 10002:udp -p 10000:wss -p 10003
# Only listen on the ZeroTier's LIASOn1 interface by default.
#
#Ice.Default.Host=10.147.19.157
Ice.Default.Host=localhost

```

# Compiling the client and server ⭐️

```Console

./gradlew build

```

# Run the java jars to execute the program 🎇

Configure the host of the server in the config.server file

```
java -jar server/build/libs/server.jar
java -jar client/build/libs/client.jar
```

The client runs and exits without producing any output; however, in the server windo