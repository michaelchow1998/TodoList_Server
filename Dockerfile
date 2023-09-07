FROM openjdk:17
ADD target/TodoList-0.0.1-SNAPSHOT.jar TodoList-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-Dspring.profiles.active=dev","-jar","TodoList-0.0.1-SNAPSHOT.jar"]