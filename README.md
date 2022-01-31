#### Prerequisites to run the project:

- [jdk 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [docker engine](https://docs.docker.com/engine/install/)
- [docker compose](https://docs.docker.com/compose/install/)
 > Spin up the development environment through: `docker-compose up -d`

 > Start the project through starting all the defined microservices (starting with config-server)

### API documentation:
- Add product
```http request
POST http://localhost:8080/command/create
```
Request Body: 
```
  {
    "ref": "string",
    "name": "string",
    "description": "string",
    "price": "number",
    "quantity": "integer"
  }
 ```

- Buy Product
```http request
POST http://localhost:8080/command/buy/<product-ref>
```
- Refill Product
```http request
POST http://localhost:8080/query/
```
- Get All Products
```http request
GET http://localhost:8080/command/refill/<product-ref>?quantity=<quantity>
```
- Get Product by reference
```http request
GET http://localhost:8080/query/<product-ref>
```
- Clear query database
```http request
DELETE localhost:8080/query/purge
```
- Clear command database 
```http request
DELETE localhost:8080/command/purge
```

### Application Architecture
![cqrs](https://user-images.githubusercontent.com/56363189/151734627-f1bf0736-cc05-4708-bbc8-529b933b4df7.png)


### Service registry (Eureka server)
![Screen Shot 2022-01-31 at 3 02 33 AM](https://user-images.githubusercontent.com/56363189/151732207-15630ad5-c067-41a4-8ef2-f4dbaede84f7.png)

### Config Server
[Github repository](https://github.com/AyariAhmed/springboot-microservices-config-server) containing the microservices configuration.
