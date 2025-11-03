#!/bin/bash 
# creazione immagini cargorobot e cargoservice 
echo CREAZIONI IMMAGINI DEL SISTEMA
echo creazione immagine cargorobot
cd ../sprint1/cargorobot_sprint1
pwd
chmod +x gradlew
./gradlew distTar
docker build -t cargorobot:1.0 .

echo creazione immagine cargoservice 
cd ../../sprint3/cargoservice_sprint3
pwd 
chmod +x gradlew
./gradlew distTar
docker build -t cargoservice:1.0 .

echo creazione immagine ioport 
cd ../../sprint2/ioport_sprint2
pwd 
chmod +x gradlew
./gradlew distTar
docker build -t ioport:1.0 .

# creazione immagine webgui 
echo creazioni delle immagini di webgui
cd ../../sprint3/cargogui_backend_sprint3 
pwd
chmod +x gradlew
./gradlew distTar
docker build -t webgui-backend:1.0 .
cd ../gui
docker build -t webgui-frontend:1.0 .
cd ../../
echo FINITO
