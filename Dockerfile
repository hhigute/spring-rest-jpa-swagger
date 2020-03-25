FROM java:8
EXPOSE 7001
ADD /target/spring-rest-jpa-swagger-0.0.1-SNAPSHOT.jar spring-rest-jpa-swagger.jar
ENTRYPOINT ["java", "-jar", "spring-rest-jpa-swagger.jar"]