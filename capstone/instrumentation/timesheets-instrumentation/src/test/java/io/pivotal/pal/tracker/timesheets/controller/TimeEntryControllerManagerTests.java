package io.pivotal.pal.tracker.timesheets.controller;

import io.pivotal.pal.tracker.timesheets.controller.TimeEntryControllerManager;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeEntryControllerManagerTests {
    @Test
    public void defaultStatusUp() {
        TimeEntryControllerManager manager = new TimeEntryControllerManager();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusUp() {
        TimeEntryControllerManager manager = new TimeEntryControllerManager();

        manager.setStatusDown();
        manager.setStatusUp();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusDown() {
        TimeEntryControllerManager manager = new TimeEntryControllerManager();

        manager.setStatusDown();

        assertThat(manager.getStatus()).isEqualTo("DOWN");
    }
}
