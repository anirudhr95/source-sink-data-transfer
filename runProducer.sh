#!/bin/sh

nohup java  -jar -Xmx3200M producer-1.0-SNAPSHOT.jar -s /data/finalDataSet4Sept2019 &

echo $(($(date +%s%N)/1000000))