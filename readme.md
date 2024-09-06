
# AuthenticationAPI

## Feature

- Login
- Register
- GitHub Action
- Docker


## Coverage 

- 0%

## Links 
- Github  
  - Back End Java API : https://github.com/Contunder/AuthenticationAPI  
  - Front End React : https://github.com/Contunder/AuthenticationAPI  
  

- Docker
  - Docker Back : docker push valden01/authentication:tagname  
    - Back Port Mapping : 8080
  - Docker Front : docker push valden01/authentication:tagname
    - Front Port Mapping : 3000

## For Use

- Java 21.0.2
- Maven 3.9.2
- Spring Boot 3.3.0

## Config Database

- In /src/main/resources/application.properties
- Change spring.datasource with your config 
- The Database will be automatically create at first launch
- Add Role in the table

>ROLE_USER  
>ROLE_ADMIN

- If you have any problems you can't change acces in SecurityConfig 

> .anyRequest().authenticated() -> .anyRequest().permitAll()

## Config project install

- mvn clean install

## SWAGGER AT 

- http://localhost:8080/swagger/authentication

## PostMan files

- authentication.postman_collection.json
