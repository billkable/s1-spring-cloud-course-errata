package io.pivotal.pal.tracker.timesheets.projectclient;

import io.micrometer.core.instrument.MeterRegistry;
import io.pivotal.pal.tracker.instrumentation.CacheHitCount;
import io.pivotal.pal.tracker.instrumentation.TraceTimer;
import io.pivotal.pal.tracker.timesheets.TimeEntryController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class ProjectClientCacheAspect {
    private final TraceTimer cacheHitTraceTimer;
    private final CacheHitCount hitCount;
    private final CacheHitCount missCount;

    public ProjectClientCacheAspect(MeterRegistry meterRegistry) {
        cacheHitTraceTimer = new TraceTimer(meterRegistry,
                TimeEntryController.class,
                "timesheets.projectclient.cache.hit.timer",
                "Time Entry Project Client cache hit timer");
        hitCount = new CacheHitCount(meterRegistry,
                "timesheets.projectclient.cache.hit.count",
                "Time Entry Project Client cache hit count");
        missCount = new CacheHitCount(meterRegistry,
                "timesheets.projectclient.cache.miss.count",
                "Time Entry Project Client cache miss count");
    }

    @Around("execution(io.pivotal.pal.tracker.timesheets.ProjectInfo " +
            "io.pivotal.pal.tracker.timesheets.ProjectClientCache.get(long))")
    public Object traceCacheHit(ProceedingJoinPoint pjp) throws Throwable {
        Object cachedValue = cacheHitTraceTimer.trace(pjp);

        if (cachedValue == null) {
            missCount.count();
        } else {
            hitCount.count();
        }

        return cachedValue;
    }
}
