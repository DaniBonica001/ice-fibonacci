# ice-fibonacci

Project with ICE: distributed programming, making a fibonacci algorithm and implementing Callback design pattern

# Integrantes 📋
- Daniela Bonilla Cáceres
- Carlos Arturo Diaz Artiaga

# Asignación 📚

## Parte 1
Implementar en bash o un esquema sencillo (e.g., escribiendo a un archivo) que permita :
- Determinar el número de clientes tales que, al hacer el envío de sus mensajes al mismo tiempo para calcular la serie de fibonacci de números grandes, empieza a aparecer la excepción de timeout.
- Evidenciar cómo responde el server cuando muchos clientes envían mensajes al mismo tiempo con números enteros grandes (i.e., hay o no hay concurrencia).

## Parte 2
Modificar el server:

1. Con multihilos para que pueda responder a múltiples solicitudes de distintos clientes, concurrentemente. Esta concurrencia es virtual o es real? cómo puede evidenciarlo? adjunte un screenshot de la prueba.
2. Responda, con esta nueva versión, los dos puntos de la Parte I.
3. Para que permita a un cliente "registrarse", con el hostname y lo necesario para que le hagan callback.
4. En cuanto a los mensajes, si el mensaje recibido:

    a. Empieza con **"List clients"**, debe retornar la lista de los clientes (hostnames o su prefijo) registrados en el server.

    b. Empieza con **"To X:"**, debe enviar lo que sigue del mensaje a X, donde X es el hostname (o su prefijo) destino.

    c. Empieza con **"BC"** (broadcast), el mensaje debe devolverlo el server a TODOS los clientes registrados en él.

# Configuración de la IP del servidor 🖥️

Para poder ejecutar nuestro programa en ordenadores remotos, hay 2 archivos que debemos modificar: **config.server y config.client**. Esto con el fin de poner la dirección IP de la computadora que será el Servidor en el atributo **Ice.Default.Host**. Si el atributo **Ice.Default.Host=localhost**, el programa se ejecutará localmente.

### server/src/main/resources/config.server ###

```
#
# The server creates one single object adapter with the name
# "Hello". The following line sets the endpoints for this
# adapter.
#
# Printer.Endpoints=tcp -p 9099
#
# Only listen on the ZeroTier's LIASOn1 interface by default.
# 10.147.19.157 - xhgrid1
# 10.147.19.34 - xhgrid3
# 10.147.19.26 - xhgrid4
#
#Ice.Default.Host=localhost

Ice.Default.Host=10.147.19.157
Service.Endpoints = default -p 8000
Ice.ThreadPool.Server.Size=3

```

### client/src/main/resources/config.client ###

```
#
# The client reads this property to create the reference to the
# "hello" object in the server.
#
# Printer.Proxy=SimplePrinter:tcp -p 9099

#
# Uncomment to use the WebSocket transports instead.
#
#Hello.Proxy=hello:ws -p 10002:udp -p 10000:wss -p 10003


# Only listen on the ZeroTier's LIASOn1 interface by default.
# 10.147.19.157 - xhgrid1
# 10.147.19.34 - xhgrid3
# 10.147.19.26 - xhgrid4

Ice.Default.Host=10.147.19.157
#Ice.Default.Host=localhost

#Callback
CallbackReceiver.Endpoints = tcp -h hgrid3 -p 10008
Server.Proxy = ChatManager: default -p 8000

```

# Compilar el cliente y el servidor ⭐️

```Console

./gradlew build

```

# Ejecutar los .jar para ejecutar el programa 🎇

Configure el host del servidor en el archivo config.server

```
java -jar server.jar
java -jar client.jar

```

