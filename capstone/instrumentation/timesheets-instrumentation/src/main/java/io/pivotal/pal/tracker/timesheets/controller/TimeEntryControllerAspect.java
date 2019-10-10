package io.pivotal.pal.tracker.timesheets.controller;

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
public final class TimeEntryControllerAspect {
    private final TraceTimer traceTimer;
    private final ExceptionCount exceptionCount;
    private final HealthCheckGauge healthCheckGauge;

    public TimeEntryControllerAspect(MeterRegistry meterRegistry) {
        traceTimer = new TraceTimer(meterRegistry,
                TimeEntryController.class,
                "timesheets.controller.create.timer",
                "Time Entry controller create timer");
        exceptionCount = new ExceptionCount(meterRegistry,
                "timesheets.controller.exception.count",
                "Time Entry controller exceptions count");
        healthCheckGauge = new HealthCheckGauge(meterRegistry,
                "timesheets.controller.health.status",
                "Time Entry Controller Health Status");
    }

    @Around("execution(org.springframework.http.ResponseEntity<io.pivotal.pal.tracker.timesheets.TimeEntryInfo> " +
            "io.pivotal.pal.tracker.timesheets.TimeEntryController.create" +
            "(io.pivotal.pal.tracker.timesheets.TimeEntryForm))")
    public Object trace(ProceedingJoinPoint pjp) throws Throwable {
        return traceTimer.trace(pjp);
    }

    @After("execution(" +
            "org.springframework.http.ResponseEntity " +
            "io.pivotal.pal.tracker.timesheets.controller.TimeEntryControllerAdvice.handleFatalControllerException())")
    public void countException() {
        exceptionCount.countException();
    }

    @Around("execution(" +
            "org.springframework.boot.actuate.health.Health TimeEntryControllerHealthIndicator.health())")
    public Health reportHealthIndicator(ProceedingJoinPoint pjp) {
        return healthCheckGauge.reportHealthIndicator(pjp);
    }
}
