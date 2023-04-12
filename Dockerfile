
#
# Build stage
#
FROM gradle:7.6.1 AS build
COPY --chown=gradle:gradle . /home/gradle
RUN gradle build || return 1

#
# Package stage
#
FROM openjdk:11-jre-slim
COPY --from=build /home/app/target/getyourway-0.0.1-SNAPSHOT.jar /usr/local/lib/demo.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/demo.jar"]