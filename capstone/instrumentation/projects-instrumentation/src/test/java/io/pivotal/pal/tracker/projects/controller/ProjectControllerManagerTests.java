package io.pivotal.pal.tracker.projects.controller;

import io.pivotal.pal.tracker.projects.controller.ProjectControllerManager;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ProjectControllerManagerTests {
    @Test
    public void defaultStatusUp() {
        ProjectControllerManager manager = new ProjectControllerManager();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusUp() {
        ProjectControllerManager manager = new ProjectControllerManager();

        manager.setStatusDown();
        manager.setStatusUp();

        assertThat(manager.getStatus()).isEqualTo("UP");
    }

    @Test
    public void setStatusDown() {
        ProjectControllerManager manager = new ProjectControllerManager();

        manager.setStatusDown();

        assertThat(manager.getStatus()).isEqualTo("DOWN");
    }
}
