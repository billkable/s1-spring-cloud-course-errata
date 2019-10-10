package io.pivotal.pal.tracker.timesheets.repository;

import io.pivotal.pal.tracker.instrumentation.RunStatus;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

public class TimeEntryRepositoryHealthIndicator implements HealthIndicator {
    private final TimeEntryRepositoryManager manager;

    public TimeEntryRepositoryHealthIndicator(TimeEntryRepositoryManager manager) {
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
