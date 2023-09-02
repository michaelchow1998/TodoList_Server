# TodoList API

## How to setup the application
1. create a table in mysql named todo_list
2. modify application.properties 
   1. enter your mysql username 
   2. enter your mysql pw
   3. enter your table name in spring.datasource.url in <table_name>
      1. e.g mysql://localhost:3306/table_name to mysql://localhost:3306/todo_list

```yaml
spring.jpa.generate-ddl=true
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://localhost:3306/todo_list?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC
spring.datasource.username=
spring.datasource.password=
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

springdoc.api-docs.path=/api-docs

```
## How to get the api swagger doc
swagger: http://localhost:8080/swagger-ui/index.html
