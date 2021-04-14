[![Build Status](https://travis-ci.org/heidiks/spring-payments-transactions-sample.svg?branch=main)](https://travis-ci.org/heidiks/spring-payments-transactions-sample)

# spring-payments-transactions-sample

### Stack:
- Spring Boot 2.4.4
- Lombok
- H2

### Require
- JDK 11
- Maven
- Docker


### Run local
```sh
$
$ mvn clean install
$ mvn spring-boot:run
```

### Run with docker
```sh
$
$ mvn clean package
$
$ docker build -t transaction-app .
$ docker-compose up
```

### Swagger
```
http://localhost:8080/swagger-ui/#/
```


### Configuration (Optional)
For h2 settings change on **application.properties** file.
