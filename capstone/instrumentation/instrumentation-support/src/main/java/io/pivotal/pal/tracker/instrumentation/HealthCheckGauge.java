package io.pivotal.pal.tracker.instrumentation;

import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.boot.actuate.health.Health;

import java.util.concurrent.atomic.AtomicInteger;

public class HealthCheckGauge {
    private final AtomicInteger healthStatusCode;

    public HealthCheckGauge(MeterRegistry meterRegistry,
                            String metricName,
                            String metricDescription) {

        healthStatusCode = new AtomicInteger(200);

        Gauge
            .builder(metricName,healthStatusCode,AtomicInteger::intValue)
            .description(metricDescription)
            .register(meterRegistry);
    }

    public Health reportHealthIndicator(ProceedingJoinPoint pjp) {
        Health health = null;

        try {
            health = (Health)pjp.proceed();

            healthStatusCode.set(
                    health.getStatus().getCode().equals("UP")?
                            200:
                            503
            );

        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return health;
    }
}
