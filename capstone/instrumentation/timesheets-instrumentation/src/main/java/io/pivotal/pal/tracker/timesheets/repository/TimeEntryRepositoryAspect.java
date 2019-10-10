package io.pivotal.pal.tracker.timesheets.repository;

import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.instrumentation.ExceptionCount;
import io.pivotal.pal.tracker.instrumentation.HealthCheckGauge;
import io.pivotal.pal.tracker.instrumentation.TraceTimer;
import io.pivotal.pal.tracker.timesheets.TimeEntryController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.actuate.health.Health;

@Aspect
public final class TimeEntryRepositoryAspect {
    private final TraceTimer traceTimer;
    private final ExceptionCount exceptionCount;
    private final HealthCheckGauge healthCheckGauge;

    public TimeEntryRepositoryAspect(MeterRegistry meterRegistry) {
        traceTimer = new TraceTimer(meterRegistry,
                TimeEntryController.class,
                "timesheets.repository.create.timer",
                "Time Entry repository create timer");
        exceptionCount = new ExceptionCount(meterRegistry,
                "timesheets.repository.exception.count",
                "Time Entry repository exceptions count");
        healthCheckGauge = new HealthCheckGauge(meterRegistry,
                "timesheets.repository.health.status",
                "Time Entry Repository Health Status");
    }

    @Around("execution(TimeEntryRecord TimeEntryDataGateway.create(TimeEntryFields))")
    public Object trace(ProceedingJoinPoint pjp) throws Throwable {
        return traceTimer.trace(pjp);
    }

    @After("execution(" +
            "org.springframework.http.ResponseEntity " +
            "io.pivotal.pal.tracker.timesheets.controller.TimeEntryControllerAdvice.handleFatalRepositoryException())")
    public void countException() {
        exceptionCount.countException();
    }

    @Around("execution(" +
            "org.springframework.boot.actuate.health.Health TimeEntryRepositoryHealthIndicator.health())")
    public Health reportHealthIndicator(ProceedingJoinPoint pjp) {
        return healthCheckGauge.reportHealthIndicator(pjp);
    }
}
