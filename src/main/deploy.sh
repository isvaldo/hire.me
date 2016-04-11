#!/bin/bash
#container do redis [Required by testes]
docker run -d --name redis -p 6379:6379 redis
#Pega ip na configuração do container do redis
export REDIS_PORT_6379_TCP_ADDR = docker inspect --format '{{ .NetworkSettings.IPAddress }}' redis
#Testes & dcoker img
mvn package docker:build
#container da aplicacao no ar
docker run -ti --rm --link redis:redis -p 81:8080 shortener/shortener
