package io.pivotal.pal.tracker.instrumentation;

import org.assertj.core.api.Assertions;
import org.junit.Test;

public class StatusTests {

    @Test
    public void defaultStatusUp() {
        ComponentStatus manager =
                new ComponentStatus();

        Assertions.assertThat(manager.getStatus()).isEqualTo(RunStatus.UP);
    }

    @Test
    public void setStatusUp() {
        ComponentStatus manager =
                new ComponentStatus();

        manager.setStatusDown();

        manager.setStatusUp();

        Assertions.assertThat(manager.getStatus()).isEqualTo(RunStatus.UP);
    }

    @Test
    public void setStatusDown() {
        ComponentStatus manager =
                new ComponentStatus();

        manager.setStatusDown();

        Assertions.assertThat(manager.getStatus()).isEqualTo(RunStatus.DOWN);
    }
}
