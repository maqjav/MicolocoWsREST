# MicolocoWsREST

MicolocoWsREST is an example of an implementation using Jersey with Spring for servlet 3.0 and Java 8.

This example includes an embebbed database (HSQLDB) with some information to play with. Using the test module you can test every single web service method with Grizzly server, without having to deploy your app in other container.

### Version
1.0.0

### Tech

Dillinger uses a number of open source projects to work properly:

* [Jersey] - REST web services
* [Genson] - here it's only used to automatize the des/serialization of messages.
* [Spring] - database configuration and injection of resources.
* [Hibernate] - database control.
* [Tomcat] - includes support for tomcat servers.
* [Servlet 3.0] - annotations vs deployment descriptor.
* [HSQLDB] - local database.

And of course Dillinger itself is open source with a [public repository][dill]
 on GitHub.

### Installation

You need Maven 3 installed globally:

```sh
$ mvn install -DskipTests
```

### Todos

 - Improve hibernate mapping

   [Jersey]: <https://jersey.java.net/>
   [Genson]: <http://owlike.github.io/genson/Documentation/UserGuide/>
   [Spring]: <https://spring.io/>
   [Hibernate]: <http://hibernate.org/>
   [Tomcat]: <http://tomcat.apache.org/>
   [Servlet 3.0]: <https://community.oracle.com/docs/DOC-983211>
   [HSQLDB]: <http://hsqldb.org/>
