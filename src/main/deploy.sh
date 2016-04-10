#!/bin/bash
# Delete all containers
docker rm $(docker ps -a -q)
# Delete all images
docker rmi $(docker images -q)
mvn package docker:build
docker run -d --name redis -p 6379:6379 redis
docker run -ti --rm --link redis:redis -p 81:8080 shortener/shortener
