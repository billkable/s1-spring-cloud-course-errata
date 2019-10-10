package io.pivotal.pal.tracker.registration;

import io.pivotal.pal.tracker.projects.repository.ProjectDataGateway;
import io.pivotal.pal.tracker.projects.repository.ProjectFields;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

import static io.pivotal.pal.tracker.projects.repository.ProjectFields.projectFieldsBuilder;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan({
        "io.pivotal.pal.tracker.accounts",
        "io.pivotal.pal.tracker.restsupport",
        "io.pivotal.pal.tracker.projects",
        "io.pivotal.pal.tracker.users"
})
public class RegistrationApp {
    public static void main(String[] args) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(RegistrationApp.class, args);
    }

    private final ProjectDataGateway gateway;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public RegistrationApp(ProjectDataGateway gateway) {
        this.gateway = gateway;
    }

    @PostConstruct
    public void init() {
        // Make sure there is repository in the registration server when
        // it starts.
        ProjectFields project = projectFieldsBuilder()
                .accountId(1)
                .name("Basket Weaving")
                .build();
        logger.info("**********************************");
        logger.info("Creating project: " + project);
        logger.info("**********************************");
        gateway.create(project);
    }
}
