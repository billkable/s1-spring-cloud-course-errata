package io.pivotal.pal.tracker.timesheets;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestOperations;

import java.util.TimeZone;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
@ComponentScan({"io.pivotal.pal.tracker.timesheets", "io.pivotal.pal.tracker.restsupport"})
public class TimesheetsApp {

    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(TimesheetsApp.class, args);
    }

    @Bean
    ProjectClientCache projectClientCache() {
        return new ProjectClientCache();
    }

    @Bean
    ProjectClient projectClient(
            RestOperations restOperations,
            @Value("${registration.server.endpoint}") String registrationEndpoint
    ) {
        return new ProjectClient(restOperations,
                                registrationEndpoint,
                                projectClientCache());
    }
}
