# Spring Security example

## Username/Password Authentication

Based on Spring documentation
* [Username/Password Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/index.html)
* [Basic Authentication](https://docs.spring.io/spring-security/reference/servlet/authentication/passwords/basic.html)

### Run App 

* run BD

podman
```
docker-compose -f db-compose.yaml up
```
docker
```
docker compose -f db-compose.yaml up
```

* build App

```
mvn clean install
```

* run App

```
java -jar target/simpleauth-0.0.1-SNAPSHOT.jar
```

### Check working App

User register

```
curl -X POST http://localhost:8080/auth/register \
     -H "Content-Type: application/json" \
     -d '{
           "name": "user1",
           "email": "user1@x.com",
           "password": "password11"
         }'
```

User login

```
curl -X POST http://localhost:8080/auth/login \
     -H "Content-Type: application/json" \
     -d '{
           "email": "user1@x.com",
           "password": "password11"
         }'
```

Get current user info

```
curl -X get http://localhost:8080/auth/info
```

Task create for current user

```
curl -X POST http://localhost:8080/tasks \
     -H "Content-Type: application/json" \
     -d '{
           "description": "main task One",
           "status": "created"
         }'
```

Get Task list for current user

```
curl -X get http://localhost:8080/tasks
```

User logout

```
curl -X get http://localhost:8080/auth/logout
```