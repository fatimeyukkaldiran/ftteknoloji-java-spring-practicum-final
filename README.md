# FT TEKNOLOJİ SPRING BOOT PRACTICUM FINAL PROJECT


___
### Spring Boot Application

---
This project provides to purchase and sales foreign currency.

### Project Detail
[Project Detail](https://github.com/fatimeyukkaldiran/ftteknoloji-java-spring-practicum-final/blob/master/FT-Teknoloji-Final-Project.pdf)

___
The application has 2 apis
* AccountAPI
* ExchangeAPI


### Tech Stack

---
- Java 17
- Spring Boot
- Spring Data JPA
- Maven
- MapStruct
- Restful API
- Swagger documentation
- PostgreSql database
- JWT

### Prerequisites

---
- Maven


### Run & Build

---
run & build the application.

#### Maven

*$PORT: 8080*
```ssh
$ cd ftteknoloji-java-spring-practicum-final/account-service
$ mvn clean install
$ mvn spring-boot:run
```
*$PORT: 8081*
```ssh
$ cd ftteknoloji-java-spring-practicum-final/exchange-service
$ mvn clean install
$ mvn spring-boot:run
```

### Swagger UI will be run on this url
`http://localhost:${8080}/swagger-ui.html`
`http://localhost:${8081}/swagger-ui.html`
