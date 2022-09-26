FROM openjdk:17.0.2-jdk as deploy
MAINTAINER SANDEEP KOIRALA
WORKDIR /user
COPY ./target/*.jar user.jar
CMD ["java","-jar","user.jar"]
