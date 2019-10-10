package io.pivotal.pal.tracker.projects.controller;

import io.pivotal.pal.tracker.projects.controller.exceptions.ProjectControllerFatalException;
import io.pivotal.pal.tracker.projects.repository.exceptions.ProjectRepositoryFatalException;
import io.pivotal.pal.tracker.projects.repository.ProjectRepositoryManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ProjectControllerManager.class);
    private final ProjectControllerManager controllerManager;
    private ProjectRepositoryManager repositoryManager;

    public ProjectControllerAdvice(ProjectControllerManager controllerManager,
                                   ProjectRepositoryManager repositoryManager) {
        this.controllerManager = controllerManager;
        this.repositoryManager = repositoryManager;
    }

    @ExceptionHandler(value = ProjectControllerFatalException.class)
    public ResponseEntity handleFatalControllerException() {

        logger.error("FATAL EXCEPTION during web request." +
                        "Setting ProjectController to DOWN state.");

        controllerManager.setStatusDown();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(value = ProjectRepositoryFatalException.class)
    public ResponseEntity handleFatalRepositoryException() {

        logger.error("FATAL EXCEPTION during web request." +
                        "Setting ProjectRepository and Controller to DOWN state.");

        controllerManager.setStatusDown();
        repositoryManager.setStatusDown();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
