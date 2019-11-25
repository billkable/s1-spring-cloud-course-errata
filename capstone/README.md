# README

This codebase is a demo codebase for integrated Spring Cloud
project.

## Build Tools

This codebase supports use of either Maven or Gradle.
The basic build artifacts are provided for you.

The codebase is set up as a multi-module build.

## Codebase

The `applications` directory contains the distributed application
Spring Boot Applications `timesheets-server` and `registration-server`.

The `components` directory contains components used by the Spring Boot
applications.

The `instrumention` directory contains components used to instrument
your Spring Boot applications,
such as:

- Timers
- Controller exception handlers
- Actuator health check indicators
- Actuator management endpoints

The `platform-services` directory contains artifacts necessary for
runtime concerns:

-   `middleware`: helper scripts for running backing middleware
-   `monitoring`: monitoring tools, such as Spring Boot Admin Server, 
    Hystrix Dashboard, 
    helpers for running Prometheus and Grafana
-   `scripts`: http client scripts, such as REST and load test.
-   `spring-cloud`: Eureka and Config Server

## Running Things

To run all the things,
you need to:

1.  Set up config server - make sure to set the
    `platform-services/spring-cloud/config-server/src/main/resources/application.properties` ->
    `spring.cloud.config.server.native.searchLocations` to the project `config` path.

1.  Startup Rabbit MQ.

Run the following as Spring Boot applications,
*in order*:

1.  Startup `platform-services/spring-cloud/config-server`.

1.  Startup `platform-services/spring-cloud/eureka-server`.

1.  Startup `platform-services/monitoring/hystrix-dashboard-server`.

1.  Startup `applications/registration-server`.

1.  Startup `applications/timesheets-server`.

1.  Startup `platform-services/monitoring/spring-boot-admin-server`

## Scripts

The `platform-services/scripts/rest` directory includes tools for executing REST requests
over http.
Four tools are supported:

- cURL
- HTTPie
- Postman
- IntelliJ REST client

Both Postman and IntelliJ REST clients include correlations between
requests and test assertions that allow to run as automated acceptance
test script.

The `platform-services/scripts/load-tests` directory includes tools for executing REST requests
over http.
Two tools are supported:

- Apache Jmeter
- Gatling

