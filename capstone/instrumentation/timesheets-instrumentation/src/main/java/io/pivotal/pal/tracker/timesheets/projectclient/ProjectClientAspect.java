package io.pivotal.pal.tracker.timesheets.projectclient;

import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.instrumentation.CacheHitCount;
import io.pivotal.pal.tracker.instrumentation.TraceTimer;
import io.pivotal.pal.tracker.timesheets.TimeEntryController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ProjectClientAspect {
    private final TraceTimer projectClientHitTimer;

    public ProjectClientAspect(MeterRegistry meterRegistry) {
        projectClientHitTimer = new TraceTimer(meterRegistry,
                TimeEntryController.class,
                "timesheets.projectclient.get.timer",
                "Time Entry Project Client get timer");;
    }

    @Around("execution(io.pivotal.pal.tracker.timesheets.ProjectInfo " +
            "io.pivotal.pal.tracker.timesheets.ProjectClient.getProject(long))")
    public Object traceGet(ProceedingJoinPoint pjp) throws Throwable {
        return projectClientHitTimer.trace(pjp);
    }
}
