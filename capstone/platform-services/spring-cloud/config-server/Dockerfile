FROM adoptopenjdk/openjdk11:jdk-11.0.4_11-alpine-slim
VOLUME /tmp
COPY build/libs/config-server-2.1.116.jar config-server-2.1.116.jar
ENTRYPOINT ["java","-jar","./config-server-2.1.116.jar"]