#!/bin/bash

./gradlew clean assemble

PROJECT_DIR=$PWD

cd ${PROJECT_DIR}/platform-services/spring-cloud/config-server
docker build -t io.pivotal.education/config-server .

cd ${PROJECT_DIR}/platform-services/spring-cloud/eureka-server
docker build -t io.pivotal.education/eureka-server .

cd ${PROJECT_DIR}/platform-services/monitoring/spring-boot-admin-server
docker build -t io.pivotal.education/spring-boot-admin-server .

cd ${PROJECT_DIR}/platform-services/monitoring/hystrix-dashboard-server
docker build -t io.pivotal.education/hystrix-dashboard-server .

cd ${PROJECT_DIR}/platform-services/monitoring/turbine-amqp-server
docker build -t io.pivotal.education/turbine-amqp-server .

cd ${PROJECT_DIR}/applications/registration-server
docker build -t io.pivotal.education/registration-server .

cd ${PROJECT_DIR}/applications/timesheets-server
docker build -t io.pivotal.education/timesheets-server .

docker-compose up --no-start --scale registration=2 --scale timesheets=2
docker-compose start rabbitmq
docker-compose exec rabbitmq rabbitmq-plugins enable rabbitmq_tracing
docker-compose stop rabbitmq

cd ${PROJECT_DIR}

./start.sh