package io.pivotal.pal.tracker.projects;

import io.pivotal.pal.tracker.projects.repository.ProjectDataGateway;
import io.pivotal.pal.tracker.projects.repository.ProjectFields;
import io.pivotal.pal.tracker.projects.repository.ProjectRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static io.pivotal.pal.tracker.projects.ProjectInfo.projectInfoBuilder;
import static io.pivotal.pal.tracker.projects.repository.ProjectFields.projectFieldsBuilder;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/projects")
public class ProjectController {

    private final ProjectDataGateway gateway;

    public ProjectController(ProjectDataGateway gateway) {
        this.gateway = gateway;
    }

    @PostMapping
    public ResponseEntity<ProjectInfo> create(@RequestBody ProjectForm form) {
        ProjectRecord record = gateway.create(formToFields(form));
        return new ResponseEntity<>(present(record), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectInfo>> list(@RequestParam long accountId) {
        return ResponseEntity.ok(gateway.findAllByAccountId(accountId)
                .stream()
                .map(this::present)
                .collect(toList()));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectInfo> get(@PathVariable long projectId) {
        ProjectRecord record = gateway.find(projectId);

        if (record != null) {
            return ResponseEntity.ok(present(record));
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    private ProjectFields formToFields(ProjectForm form) {
        return projectFieldsBuilder()
                .accountId(form.accountId)
                .name(form.name)
                .active(form.active)
                .build();
    }

    private ProjectInfo present(ProjectRecord record) {
        return projectInfoBuilder()
                .id(record.id)
                .accountId(record.accountId)
                .name(record.name)
                .active(record.active)
                .info("project info")
                .build();
    }
}
