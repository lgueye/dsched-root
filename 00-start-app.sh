#!/bin/bash

docker stop $(docker ps -a -q) ; docker system prune -f ; mvn clean package -Pdemo
