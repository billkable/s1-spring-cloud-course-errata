package io.pivotal.pal.tracker.timesheets.repository;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TimeEntryRepositoryManagerTests {
    @Test
    public void defaultStatusUp() {
        TimeEntryRepositoryManager manager = new TimeEntryRepositoryManager();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusUp() {
        TimeEntryRepositoryManager manager = new TimeEntryRepositoryManager();

        manager.setStatusDown();
        manager.setStatusUp();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusDown() {
        TimeEntryRepositoryManager manager = new TimeEntryRepositoryManager();

        manager.setStatusDown();

        assertThat(manager.getStatus()).isEqualTo("DOWN");
    }
}
