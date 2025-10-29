FROM eclipse-temurin:17.0.5_8-jre-focal as builder

RUN apt-get update -y
## RUN apt-get install -y wiringpi
RUN apt-get install -y python3 python3-pip
RUN pip install RPi.GPIO
## EXPOSE 8720
## ADD extracts the tar
ADD ./build/distributions/ioport-1.0.tar /
WORKDIR /ioport-1.0/bin

COPY ./*.pl ./
COPY ./*.json ./
COPY ./*.bin ./
COPY ./*.txt ./





CMD ["bash", "ioport"]