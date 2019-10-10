package io.pivotal.pal.tracker.timesheets.controller;

import io.pivotal.pal.tracker.timesheets.TimeEntryController;
import io.pivotal.pal.tracker.timesheets.controller.exceptions.TimeEntryControllerFatalException;
import io.pivotal.pal.tracker.timesheets.repository.exceptions.TimeEntryRepositoryFatalException;
import io.pivotal.pal.tracker.timesheets.repository.TimeEntryRepositoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TimeEntryControllerAdvice {
    private final Logger logger = LoggerFactory.getLogger(TimeEntryController.class);
    private final TimeEntryControllerManager controllerManager;
    private TimeEntryRepositoryManager repositoryManager;

    public TimeEntryControllerAdvice(TimeEntryControllerManager controllerManager,
                                     TimeEntryRepositoryManager repositoryManager) {
        this.controllerManager = controllerManager;
        this.repositoryManager = repositoryManager;
    }

    @ExceptionHandler(value = TimeEntryControllerFatalException.class)
    public ResponseEntity handleFatalControllerException() {

        logger.error("FATAL EXCEPTION during web request." +
                "Setting TimeEntryController to DOWN state.");

        controllerManager.setStatusDown();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(value = TimeEntryRepositoryFatalException.class)
    public ResponseEntity handleFatalRepositoryException() {

        logger.error("FATAL EXCEPTION during web request." +
                "Setting TimeEntryRepository and Controller to DOWN state.");

        controllerManager.setStatusDown();
        repositoryManager.setStatusDown();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

}
