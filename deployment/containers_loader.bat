@echo off
REM ===============================================
REM CARICAMENTO CONTAINER
REM ===============================================

echo CARICAMENTO CONTAINER

REM === carico i servizi MONGODB e MOSQUITTO ===
cd yamls\utils
echo Carico i servizi MONGODB e MOSQUITTO
docker-compose -f utils.yaml up -d

REM === carico i servizi BASICROBOT ===
cd ..\basicrobot
echo Carico i servizi BASICROBOT
docker-compose -f basicrobot26.yaml up -d

timeout /t 30 /nobreak >nul

REM === carico PRODUCTSERVICE ===
cd ..\productservice
echo Carico PRODUCTSERVICE
docker-compose -f productservice.yaml up -d

REM === carico CARGOROBOT ===
cd ..\cargorobot
echo Carico CARGOROBOT
docker-compose -f cargorobot.yaml up -d

REM === carico CARGOSERVICE ===
cd ..\cargoservice
echo Carico CARGOSERVICE
docker-compose -f cargoservice.yaml up -d

REM === carico WEBGUI ===
cd ..\webgui
echo Carico i server della WEBGUI
docker-compose -f webgui.yaml up -d

cd ..\..\..\

echo FINITO
pause

