package io.pivotal.pal.tracker.instrumentation;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class TraceTimer {
    private final Logger logger;
    private final Timer timer;

    public TraceTimer(MeterRegistry meterRegistry,
                      Class loggerClass,
                      String metricName,
                      String metricDescription) {

        logger = LoggerFactory.getLogger(loggerClass);

        timer = Timer
                .builder(metricName)
                .description(metricDescription)
                .publishPercentiles(0.5,0.9,0.99,0.999)
                .register(meterRegistry);
    }

    public Object trace(ProceedingJoinPoint pjp) throws Throwable {
        try {
            long startTime = System.currentTimeMillis();
            logger.trace
                    ("Executing {} start at {}",
                            pjp.getSignature().toShortString(),
                            startTime);

            Object response = pjp.proceed();
            long wallClockTime = System.currentTimeMillis() - startTime;

            logger.trace
                    ("Executing {} stop at {}",
                            pjp.getSignature().toShortString(),
                            wallClockTime);

            timer.record(Duration.of(wallClockTime, ChronoUnit.MILLIS));

            return response;

        } catch (Throwable throwable) {
            logger.error("Error thrown while tracing {}: {}",
                    pjp.getSignature().toShortString(),
                    throwable.getMessage());
            throw throwable;
        }
    }

}
