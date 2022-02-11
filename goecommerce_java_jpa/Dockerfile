FROM maven:3.6.3-jdk-11-slim AS build
RUN mkdir /project
COPY ./goecommerce /project
WORKDIR /project
RUN mvn clean package -DskipTests
 
 
FROM openjdk:11-jre
RUN mkdir /app
COPY --from=build /project/target/*.jar /app/goecommerce.jar
WORKDIR /app
#CMD "tail" "-f" "/dev/null"
CMD "java" "-jar" "/app/goecommerce.jar"
