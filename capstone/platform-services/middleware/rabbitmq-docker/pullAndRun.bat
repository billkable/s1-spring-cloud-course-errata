docker run --name rabbitmq -d -p 15671:15671 -p 15672:15672 -p 5672:5672 rabbitmq:management
docker exec rabbitmq rabbitmq-plugins enable rabbitmq_tracing