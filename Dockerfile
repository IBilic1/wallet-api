
#
# Build stage
#
FROM gradle:7.6.1 AS build
COPY --chown=gradle:gradle . /home/gradle
RUN gradle build || return 1

#
# Package stage
#
FROM openjdk:17-jdk
COPY --from=build /home/gradle/build/libs/wallet-api-0.0.1-SNAPSHOT.jar /usr/local/lib/wallet-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-Dspring.profiles.active=production","-jar","/usr/local/lib/wallet-api.jar"]