package io.pivotal.pal.tracker.timesheets.controller;

import io.pivotal.pal.tracker.instrumentation.RunStatus;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class TimeEntryControllerHealthIndicator implements HealthIndicator {
    private final TimeEntryControllerManager manager;

    public TimeEntryControllerHealthIndicator(TimeEntryControllerManager manager) {
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
