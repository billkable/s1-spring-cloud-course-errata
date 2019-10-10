package io.pivotal.pal.tracker.projects.repository;

import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.instrumentation.ExceptionCount;
import io.pivotal.pal.tracker.instrumentation.HealthCheckGauge;
import io.pivotal.pal.tracker.instrumentation.TraceTimer;
import io.pivotal.pal.tracker.projects.ProjectController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.actuate.health.Health;

@Aspect
public final class ProjectRepositoryAspect {
    private final TraceTimer traceTimer;
    private final ExceptionCount exceptionCount;
    private final HealthCheckGauge healthCheckGauge;

    public ProjectRepositoryAspect(MeterRegistry meterRegistry) {
        traceTimer = new TraceTimer(meterRegistry,
                ProjectController.class,
                "project.repo.find.timer",
                "Project repository find by id timer");
        exceptionCount = new ExceptionCount(meterRegistry,
                "project.repository.exception.count",
                "Project repository exception count");
        healthCheckGauge = new HealthCheckGauge(meterRegistry,
                "projects.repository.health.status",
                "Project Repository Health Status");
    }

    @Around("execution(" +
            "org.springframework.http.ResponseEntity<io.pivotal.pal.tracker.projects.ProjectInfo> " +
            "io.pivotal.pal.tracker.projects.repository.ProjectDataGateway.find(long))")
    public Object trace(ProceedingJoinPoint pjp) throws Throwable {
        return traceTimer.trace(pjp);
    }

    @After("execution(" +
            "org.springframework.http.ResponseEntity " +
            "io.pivotal.pal.tracker.projects.controller.ProjectControllerAdvice.handleFatalRepositoryException())")
    public void countException() {
        exceptionCount.countException();
    }

    @Around("execution(" +
            "org.springframework.boot.actuate.health.Health ProjectRepositoryHealthIndicator.health())")
    public Health reportHealthIndicator(ProceedingJoinPoint pjp) {
        return healthCheckGauge.reportHealthIndicator(pjp);
    }
}
