package io.pivotal.pal.tracker.projects.controller;

import io.pivotal.pal.tracker.instrumentation.RunStatus;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class ProjectControllerHealthIndicator implements HealthIndicator {
    private final ProjectControllerManager manager;

    public ProjectControllerHealthIndicator(ProjectControllerManager manager) {
        this.manager = manager;
    }

    @Override
    public Health health() {
        if (manager.getStatus().equals(RunStatus.UP.name())) {
            return Health.up().build();
        } else {
            return Health.down().build();
        }
    }
}
