#!/bin/bash
wget -O cm https://github.com/aerokube/cm/releases/download/1.5.6/cm_linux_amd64&&
chmod +x ./cm&&
./cm selenoid start --vnc&&
sudo ./cm selenoid-ui start&&
docker pull selenoid/vnc:chrome_70.0&&
docker pull selenoid/vnc:firefox_64.0&&
mvn clean test -Dbrowser="$1" -DbrowserVersion="$2"

