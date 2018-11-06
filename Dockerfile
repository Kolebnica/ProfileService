#sets the Base Image 
FROM openjdk:8-jre-alpine

COPY ./api/target/api-1.0-SNAPSHOT.jar /app/
COPY ./config.yaml /

CMD ["java", "-jar", "/app/api-1.0-SNAPSHOT.jar"]