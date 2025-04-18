# Spring Security example

## Username/Password Authentication

### DB 

podman
```
docker-compose -f db-compose.yaml up
```
docker
```
docker compose -f db-compose.yaml up
```

### run App

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
curl -X get http://localhost:8080/api/auth/logout
```