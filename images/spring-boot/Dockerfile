FROM openjdk:8-jdk-alpine

MAINTAINER Ludovic Delafontaine <ludovic.delafontaine@heig-vd.ch>

COPY swagger-spring-1.0.0.jar swagger-spring-1.0.0.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=container", "-jar", "/swagger-spring-1.0.0.jar"]
