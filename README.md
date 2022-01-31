#### Prerequisites to run the project:

- [jdk 11](https://www.oracle.com/java/technologies/javase/jdk11-archive-downloads.html)
- [docker engine](https://docs.docker.com/engine/install/)
- [docker compose](https://docs.docker.com/compose/install/)
 > -Spin up the development environment through: `docker-compose up -d`
<br/>
 > -Start the project through starting all the defined microservices (starting with config-server)

### API documentation:
- Add product
```http request
POST http://localhost:8080/command/create 
Request Body: 
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

