package io.pivotal.pal.tracker.projects.controller;

import io.pivotal.pal.tracker.instrumentation.ComponentStatus;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "project-controller-status")
public final class ProjectControllerManager {
    private final ComponentStatus controllerStatus;

    public ProjectControllerManager() {
        this.controllerStatus = new ComponentStatus();
    }

    @WriteOperation
    public void setStatusUp() {
        controllerStatus.setStatusUp();
    }

    @DeleteOperation
    public void setStatusDown() {
        controllerStatus.setStatusDown();
    }

    @ReadOperation
    public String getStatus() {
        return controllerStatus.getStatus().name();
    }

}
