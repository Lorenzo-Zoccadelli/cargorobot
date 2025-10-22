
FROM eclipse-temurin:17.0.5_8-jre-focal as builder
## EXPOSE 8720
## ADD extracts the tar
WORKDIR /webgui-backend
COPY build/libs/webgui-backend-1.0.jar webgui-backend.jar

## RUN apt-get update -y
## RUN apt-get install -y wiringpi
## RUN sudo apt-get install -y python

CMD ["java", "-jar", "webgui-backend.jar"]