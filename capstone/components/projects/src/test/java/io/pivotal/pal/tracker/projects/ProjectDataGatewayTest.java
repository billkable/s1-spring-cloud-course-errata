package io.pivotal.pal.tracker.projects;

import io.pivotal.pal.tracker.projects.repository.ProjectDataGateway;
import io.pivotal.pal.tracker.projects.repository.ProjectFields;
import io.pivotal.pal.tracker.projects.repository.ProjectRecord;
import org.junit.Test;

import java.util.List;

import static io.pivotal.pal.tracker.projects.repository.ProjectFields.projectFieldsBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class ProjectDataGatewayTest {

    private ProjectDataGateway gateway = new ProjectDataGateway();

    @Test
    public void testCreate() {
        ProjectFields fields = projectFieldsBuilder().accountId(1).name("aProject").build();
        ProjectRecord created = gateway.create(fields);

        assertThat(created.id).isNotNull();
        assertThat(created.name).isEqualTo("aProject");
        assertThat(created.accountId).isEqualTo(1L);
        assertThat(created.active).isTrue();

        ProjectRecord found = gateway.find(created.id);

        assertThat(found.name).isEqualTo("aProject");
        assertThat(found.accountId).isEqualTo(1L);
    }

    @Test
    public void testFindAllByAccountId() {
        ProjectRecord foundProject = gateway.create(projectFieldsBuilder().accountId(1).name("aProject").build());
        gateway.create(projectFieldsBuilder().accountId(2).name("notMyProject").build());

        List<ProjectRecord> result = gateway.findAllByAccountId(1L);

        assertThat(result.size()).isEqualTo(1);

        assertThat(result).containsExactlyInAnyOrder(foundProject);
    }
}
