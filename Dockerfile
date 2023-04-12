
#
# Build stage
#
FROM gradle:7.6.1 AS build
COPY src /home/app/src
COPY build.gradle /home/app
RUN gradle build

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/getyourway-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]