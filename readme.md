# Dolphin Platform Spring Boot Sample

![Dolphin Platform Logo](http://www.guigarage.com/wordpress/wp-content/uploads/2015/10/logo.png)

This automatically created project can be used as a basic skeleton for a Spring Boot based Dolphin Platform application.
The Maven projects contains 3 modules: 

* The __common__ module that contains the model taht is shared between client and server
* The __server__ module that contains the Spring Boot Application and a first Dolphin Platform controller 
* The __client__ module a JavaFX application that creates a view that is bound to the Dolphin Platform controller in the server module


## How to use it
The project is created as a Maven project. You can directly import it in any IDE that supports Maven (like Eclipse, Netbeans or IntelliJ).
If you want to start the application you need to start the ServerApplication class in the server module and than the ClientApplication class in the client module. The client can be started several times.

If you want to start the application from commandline without IDE support you need to call a Maven install first in the main project folder:

```
mvn install
```

Once this is done you can simply install the server from the server folder
```
cd server
mvn spring-boot:run
```

A client instance can be started by Maven, too:
```
cd client
mvn exec:java
```