package io.pivotal.pal.tracker.instrumentation;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class CacheHitCount {
    private final Counter hitCounter;

    public CacheHitCount(MeterRegistry meterRegistry,
                          String metricName,
                          String metricDescription) {
        hitCounter = Counter
                .builder(metricName)
                .description(metricDescription)
                .register(meterRegistry);
    }

    public void count() {
        hitCounter.increment();
    }
}
