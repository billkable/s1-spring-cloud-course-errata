package io.pivotal.pal.tracker.timesheets;

import com.netflix.loadbalancer.IPing;
import com.netflix.loadbalancer.PingUrl;
import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.timesheets.controller.TimeEntryControllerAspect;
import io.pivotal.pal.tracker.timesheets.projectclient.ProjectClientAspect;
import io.pivotal.pal.tracker.timesheets.projectclient.ProjectClientCacheAspect;
import io.pivotal.pal.tracker.timesheets.repository.TimeEntryRepositoryAspect;
import io.pivotal.pal.tracker.timesheets.repository.TimeEntryRepositoryHealthIndicator;
import io.pivotal.pal.tracker.timesheets.repository.TimeEntryRepositoryManager;
import io.pivotal.pal.tracker.timesheets.controller.TimeEntryControllerHealthIndicator;
import io.pivotal.pal.tracker.timesheets.controller.TimeEntryControllerManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TimeEntryInstrumentationConfig {

    @Bean
    public TimeEntryRepositoryManager timesheetsRepositoryManager() {
        return new TimeEntryRepositoryManager();
    }

    @Bean
    public TimeEntryRepositoryHealthIndicator timesheetsRepositoryHealthIndicator() {
        return new TimeEntryRepositoryHealthIndicator(timesheetsRepositoryManager());
    }

    @Bean
    public TimeEntryControllerManager timesheetsControllerManager() {
        return new TimeEntryControllerManager();
    }

    @Bean
    public TimeEntryControllerHealthIndicator timesheetsControllerHealthIndicator() {
        return new TimeEntryControllerHealthIndicator(timesheetsControllerManager());
    }

    @Bean
    public TimeEntryControllerAspect timeEntryControllerAspect(MeterRegistry meterRegistry) {
        return new TimeEntryControllerAspect(meterRegistry);
    }

    @Bean
    public TimeEntryRepositoryAspect timeEntryRepositoryAspect(MeterRegistry meterRegistry) {
        return new TimeEntryRepositoryAspect(meterRegistry);
    }

    @Bean
    public ProjectClientAspect projectClientAspect(MeterRegistry meterRegistry) {
        return new ProjectClientAspect(meterRegistry);
    }

    @Bean
    public ProjectClientCacheAspect projectClientCacheAspect(MeterRegistry meterRegistry) {
        return new ProjectClientCacheAspect(meterRegistry);
    }

    @Bean
    public IPing ribbonPing() {
        return new PingUrl(false, "/actuator/health");
    }
}
