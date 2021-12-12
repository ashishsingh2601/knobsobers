# Spring Boot Boilerplate  

## Interservice

- Merchant Interservice

## Interceptors

- Merchant Token Interceptor
- HMAC Interceptor

## Exception Handler

- [Custom AppException Handler](https://github.com/bharatpe/spring-boot-boilerplate/blob/main/src/main/java/com/springboot/boilerplate/exception/ControllerAdvisor.java#L15) 
- [Mismatch Argument Exception Handler](https://github.com/bharatpe/spring-boot-boilerplate/blob/main/src/main/java/com/springboot/boilerplate/exception/ControllerAdvisor.java#L22)
- [AppException Handler](https://github.com/bharatpe/spring-boot-boilerplate/blob/main/src/main/java/com/springboot/boilerplate/exception/ControllerAdvisor.java#L30)

## Contribution Guidelines

- Clone the project in your local machine.
- Open it in Intellij
- Edit Application Configuration, Set Environment (VM Options) -> `-Dspring.profiles.active=dev`
  <img width="1792" alt="Screenshot 2021-09-23 at 3 17 10 PM" src="https://user-images.githubusercontent.com/56909188/134486930-580061cd-7231-4f88-ab1a-6786536183a4.png">
  <img width="1241" alt="Screenshot 2021-09-23 at 3 17 26 PM" src="https://user-images.githubusercontent.com/56909188/134486957-958b5f8f-5de8-4dc9-895d-d1c31a2adff1.png">
- Install Maven
  <img width="1790" alt="Screenshot 2021-09-23 at 3 22 13 PM" src="https://user-images.githubusercontent.com/56909188/134487639-f1259a56-8959-467a-bcd6-775a600af522.png">
- Create application-dev.properties file.
- Set Environment variables in application-dev.properties file.
- Use [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) i.e feat:`message`, refactor:`message`, fix:`message`, breakingchange!:`message` with proper description.
- Run Application using Intellij shortcut (^R) or using terminal command `mvn spring-boot:run`

## Environment Variables

```
spring.datasource.url=<your MySQL DB url>
spring.datasource.username=<your MySQL username>
spring.datasource.password=<your MySQL password>
server.port=9999
super.key=<Super Key>
merchant.service.host=<merchant service domain>
```

## HTTP Methods

- GET : Fetching/Reading data
- POST: Inserting New Record
- PUT: Update Record
- DELETE: Delete Record
