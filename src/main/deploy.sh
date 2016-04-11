#!/bin/bash
#container do redis [Required by testes]
docker run -d --name redis -p 6379:6379 redis
docker start redis
#Ainda não entendi muito bem como se comunicar entre os containers
#então estou martelando esse ip, mas pelo menos, redis e sprinb boot
#estão separados
export REDIS_PORT_6379_TCP_ADDR=172.17.0.16
#Testes & docker img
mvn package docker:build
#container da aplicacao no ar
docker run -ti --rm --link redis:redis -p 81:8080 shortener/shortener
