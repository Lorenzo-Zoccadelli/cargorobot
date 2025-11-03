#!/bin/bash
echo CARICAMENTO CONTAINER
cd yamls/utils 
echo carico i servizi MONGODB e MOSQUITTO
docker-compose -f utils.yaml up

cd ../basicrobot
echo carico i servizi BASICROBOT
docker-compose -f basicrobot26.yaml up

cd ../productservice 
echo carico PRODUCTSERVICE 
docker-compose -f productservice.yaml up

cd ../cargorobot 
echo carico CARGOROBOT 
docker-compose -f cargorobot.yaml up 

cd ../cargoservice
echo carico CARGOSERVICE 
docker-compose -f cargoservice.yaml up

cd ../webgui
echo carico i server della WEBGUI
docker-compose -f webgui.yaml up
cd ../../../
echo FINITO
