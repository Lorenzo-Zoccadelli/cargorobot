@echo off
REM ===============================================
REM CARICAMENTO CONTAINER
REM ===============================================

echo CARICAMENTO CONTAINER

REM === carico i servizi MONGODB e MOSQUITTO ===
cd deployment\yamls\utils
echo Carico i servizi MONGODB e MOSQUITTO
docker-compose -f utils.yaml up

REM === carico i servizi BASICROBOT ===
cd ..\basicrobot
echo Carico i servizi BASICROBOT
docker-compose -f basicrobot26.yaml up

REM === carico PRODUCTSERVICE ===
cd ..\productservice
echo Carico PRODUCTSERVICE
docker-compose -f productservice.yaml up

REM === carico CARGOROBOT ===
cd ..\cargorobot
echo Carico CARGOROBOT
docker-compose -f cargorobot.yaml up

REM === carico CARGOSERVICE ===
cd ..\cargoservice
echo Carico CARGOSERVICE
docker-compose -f cargoservice.yaml up

REM === carico WEBGUI ===
cd ..\webgui
echo Carico i server della WEBGUI
docker-compose -f webgui.yaml up

cd ..\..\..\

echo FINITO
pause

