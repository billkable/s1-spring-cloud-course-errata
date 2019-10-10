package io.pivotal.pal.tracker.projects.repository;

import io.pivotal.pal.tracker.instrumentation.ComponentStatus;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "project-repository-status")
public final class ProjectRepositoryManager {
    private final ComponentStatus controllerStatus;

    public ProjectRepositoryManager() {
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
