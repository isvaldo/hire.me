#!/bin/bash
mvn package docker:build
docker run --name redis -d redis
docker run --name shortener -d -p 81:8080 -t shortener/shortener
