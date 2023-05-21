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

# Configuración de la IP del servidor 🖥️

Para poder ejecutar nuestro programa en ordenadores remotos, hay 2 archivos que debemos modificar: **config.server y config.client**. Esto con el fin de poner la dirección IP de la computadora que será el Servidor en el atributo **Ice.Default.Host**. Si el atributo **Ice.Default.Host=localhost**, y en el atributo **CallbackReceiver.Endpoints = tcp -h hgrid3 -p 10008** del **config.client** se reemplaza hgrid3 por localhost, el programa se ejecutará localmente.

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
Ice.ThreadPool.Server.Size=1

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
Implementamos un script que se encarga de hacer gradle build y enviar los archivos .jar a los computadores. Para ejecutar el script solo debes ejecutar el siguiente comando:

```Console

./deploy.sh

```

# Ejecutar los .jar para ejecutar el programa 🎇

Ya que nuestro script se encarga de solo enviar los archivos .jar, se ejecutan con los siguientes comandos:
```
java -jar server.jar
java -jar client.jar

```

