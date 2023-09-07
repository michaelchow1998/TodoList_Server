# TodoList API

## How to setup the application in docker
1. Install Docker Desktop
2. run ```docker-compose up```

## How to close the application in docker
1. ```docker-compose down```

## How to delete the docker database data
1. delete the folder named mysql-data

## How to enter the docker db
```
database type: mysql
port: 3307
ac: root 
pw: root
database: todo_db
```

## How to setup the application in local
1. create a table in mysql named todo_list
1. application.properties input spring.datasource.password 


## How to get the api swagger doc
swagger: http://localhost:8080/swagger-ui/index.html

## How to load the collection to postman 
1. download doc/TodoList.postman_collection.json
2. import the json to postman
