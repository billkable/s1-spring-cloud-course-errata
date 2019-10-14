#!/bin/bash

PROJECT_DIR=$PWD

docker-compose start rabbitmq

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 5672
docker-compose start config

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8888
docker-compose start eureka
#docker-compose start eureka2

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8671 -t 60
docker-compose start spring-boot-admin

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 5672
docker-compose start hystrix-dashboard

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 5672
${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8671
docker-compose start turbine

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 5672
docker-compose start zipkin

docker-compose start prom
docker-compose start exporter
docker-compose start grafana

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 5672
${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8761
docker-compose start registration

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8100
${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8101
docker-compose start timesheets

${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8200 -t 60
${PROJECT_DIR}/platform-services/scripts/docker-util/wait-for-it.sh -h localhost -p 8201 -t 60
docker-compose start nginx
