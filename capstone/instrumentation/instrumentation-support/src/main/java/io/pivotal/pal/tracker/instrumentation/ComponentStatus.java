package io.pivotal.pal.tracker.instrumentation;

import static io.pivotal.pal.tracker.instrumentation.RunStatus.DOWN;
import static io.pivotal.pal.tracker.instrumentation.RunStatus.UP;

public final class ComponentStatus {
    private RunStatus status;

    public ComponentStatus() {
        status = UP;
    }

    public void setStatusDown() {
        status = DOWN;
    }

    public void setStatusUp() {
        status = UP;
    }

    public RunStatus getStatus() {
        return status;
    }
}
