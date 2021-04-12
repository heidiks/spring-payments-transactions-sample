# spring-payments-transactions-sample

### Stack:
- Spring Boot 2.4.4
- Lombok
- MongoDB

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
For configure appid from openweather, edit *openweather.api.key* on **application.properties**.
