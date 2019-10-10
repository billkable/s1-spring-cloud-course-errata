package io.pivotal.pal.tracker.projects;

import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.projects.controller.ProjectControllerAspect;
import io.pivotal.pal.tracker.projects.repository.ProjectRepositoryAspect;
import io.pivotal.pal.tracker.projects.repository.ProjectRepositoryHealthIndicator;
import io.pivotal.pal.tracker.projects.repository.ProjectRepositoryManager;
import io.pivotal.pal.tracker.projects.controller.ProjectControllerHealthIndicator;
import io.pivotal.pal.tracker.projects.controller.ProjectControllerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectInstrumentationConfig {

    @Bean
    public ProjectRepositoryManager projectRepositoryManager() {
        return new ProjectRepositoryManager();
    }

    @Bean
    public ProjectRepositoryHealthIndicator projectRepositoryHealthIndicator() {
        return new ProjectRepositoryHealthIndicator(projectRepositoryManager());
    }

    @Bean
    public ProjectControllerManager projectControllerManager() {
        return new ProjectControllerManager();
    }

    @Bean
    public ProjectControllerHealthIndicator projectControllerHealthIndicator() {
        return new ProjectControllerHealthIndicator(projectControllerManager());
    }

    @Bean
    public ProjectControllerAspect projectControllerAspect(MeterRegistry meterRegistry) {
        return new ProjectControllerAspect(meterRegistry);
    }

    @Bean
    public ProjectRepositoryAspect projectRepositoryAspect(MeterRegistry meterRegistry) {
        return new ProjectRepositoryAspect(meterRegistry);
    }
}
