#FROM maven:3.8.6-amazoncorretto-17 AS builder
#WORKDIR /user
#COPY . .
#RUN mvn clean install

FROM openjdk:17.0.2-jdk as deploy
MAINTAINER SANDEEP KOIRALA
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root
ENV POSTGRES_DB=BlogUser
ENV APP_PROFILE=prod
WORKDIR /user
COPY ./target/*.jar user.jar
CMD ["java","-jar","user.jar"]


#FROM postgres:latest as postgres
#ENV POSTGRES_USER=root
#ENV POSTGRES_PASSWORD=root
#ENV POSTGRES_DB=blogusers