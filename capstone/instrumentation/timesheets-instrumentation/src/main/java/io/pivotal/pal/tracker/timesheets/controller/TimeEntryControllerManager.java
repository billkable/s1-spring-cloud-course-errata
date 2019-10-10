package io.pivotal.pal.tracker.timesheets.controller;

import io.pivotal.pal.tracker.instrumentation.ComponentStatus;
import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;

@Endpoint(id = "timesheets-controller-status")
public final class TimeEntryControllerManager {
    private final ComponentStatus controllerStatus;

    public TimeEntryControllerManager() {
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
