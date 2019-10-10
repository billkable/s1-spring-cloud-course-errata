package io.pivotal.pal.tracker.instrumentation;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

public class ExceptionCount {
    private final Counter exceptionCount;

    public ExceptionCount(MeterRegistry meterRegistry,
                          String metricName,
                          String metricDescription) {
        exceptionCount = Counter
                .builder(metricName)
                .description(metricDescription)
                .register(meterRegistry);
    }

    public void countException() {
        exceptionCount.increment();
    }
}
