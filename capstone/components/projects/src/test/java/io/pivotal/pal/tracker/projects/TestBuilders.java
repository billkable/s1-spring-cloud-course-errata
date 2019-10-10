package io.pivotal.pal.tracker.projects;

import io.pivotal.pal.tracker.projects.repository.ProjectFields;
import io.pivotal.pal.tracker.projects.repository.ProjectRecord;

import static io.pivotal.pal.tracker.projects.ProjectForm.projectFormBuilder;
import static io.pivotal.pal.tracker.projects.ProjectInfo.projectInfoBuilder;
import static io.pivotal.pal.tracker.projects.repository.ProjectFields.projectFieldsBuilder;
import static io.pivotal.pal.tracker.projects.repository.ProjectRecord.projectRecordBuilder;

public class TestBuilders {

    public static ProjectRecord.Builder testProjectRecordBuilder() {
        return projectRecordBuilder()
                .id(9L)
                .accountId(23L)
                .name("MyInfo")
                .active(true);
    }

    public static ProjectInfo.Builder testProjectInfoBuilder() {
        return projectInfoBuilder()
                .id(9L)
                .accountId(23L)
                .name("MyInfo")
                .active(true)
                .info("project info");
    }

    public static ProjectFields.Builder testProjectFieldsBuilder() {
        return projectFieldsBuilder()
                .accountId(23L)
                .name("MyInfo")
                .active(true);
    }

    public static ProjectForm.Builder testProjectFormBuilder() {
        return projectFormBuilder()
                .accountId(23L)
                .name("MyInfo")
                .active(true);
    }
}
