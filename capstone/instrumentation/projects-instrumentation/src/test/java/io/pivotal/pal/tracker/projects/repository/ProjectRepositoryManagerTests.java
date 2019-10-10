package io.pivotal.pal.tracker.projects.repository;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectRepositoryManagerTests {
    @Test
    public void defaultStatusUp() {
        ProjectRepositoryManager manager = new ProjectRepositoryManager();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusUp() {
        ProjectRepositoryManager manager = new ProjectRepositoryManager();

        manager.setStatusDown();
        manager.setStatusUp();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusDown() {
        ProjectRepositoryManager manager = new ProjectRepositoryManager();

        manager.setStatusDown();

        assertThat(manager.getStatus()).isEqualTo("DOWN");
    }
}
