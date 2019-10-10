package io.pivotal.pal.tracker.projects;

import io.pivotal.pal.tracker.projects.repository.ProjectDataGateway;
import io.pivotal.pal.tracker.projects.repository.ProjectRecord;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static io.pivotal.pal.tracker.projects.TestBuilders.*;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


public class ProjectControllerTest {

    private ProjectDataGateway gateway = mock(ProjectDataGateway.class);
    private ProjectController controller = new ProjectController(gateway);

    @Test
    public void testCreate() {
        ProjectRecord record = testProjectRecordBuilder().build();
        doReturn(record).when(gateway).create(any());

        ResponseEntity<ProjectInfo> result = controller.create(testProjectFormBuilder().build());

        verify(gateway).create(testProjectFieldsBuilder().build());
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(result.getBody()).isEqualTo(testProjectInfoBuilder().build());
    }

    @Test
    public void testList() {
        List<ProjectRecord> records = asList(
                testProjectRecordBuilder().id(12).build(),
                testProjectRecordBuilder().id(13).build()
        );
        doReturn(records).when(gateway).findAllByAccountId(anyLong());

        ResponseEntity<List<ProjectInfo>> result = controller.list(23);

        verify(gateway).findAllByAccountId(23L);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).containsExactlyInAnyOrder(
                testProjectInfoBuilder().id(12).build(),
                testProjectInfoBuilder().id(13).build()
        );
    }

    @Test
    public void testGet() {
        ProjectRecord record = testProjectRecordBuilder().id(99).build();
        doReturn(record).when(gateway).find(anyLong());

        ResponseEntity<ProjectInfo> result = controller.get(99);

        verify(gateway).find(99);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(testProjectInfoBuilder().id(99).build());
    }

    @Test
    public void testGet_WithNull() {
        doReturn(null).when(gateway).find(anyLong());

        ResponseEntity<ProjectInfo> result = controller.get(88);

        verify(gateway).find(88);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
