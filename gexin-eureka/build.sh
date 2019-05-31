#!/usr/bin/env bash

mvn package

docker build -t 192.168.0.10:5000/batman/gexin-eureka:latest .

docker push 192.168.0.10:5000/batman/gexin-eureka:latest