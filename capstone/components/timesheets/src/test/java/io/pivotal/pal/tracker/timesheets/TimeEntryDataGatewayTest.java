package io.pivotal.pal.tracker.timesheets;

import io.pivotal.pal.tracker.timesheets.repository.TimeEntryDataGateway;
import io.pivotal.pal.tracker.timesheets.repository.TimeEntryFields;
import io.pivotal.pal.tracker.timesheets.repository.TimeEntryRecord;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static io.pivotal.pal.tracker.timesheets.repository.TimeEntryFields.timeEntryFieldsBuilder;
import static org.assertj.core.api.Assertions.assertThat;

public class TimeEntryDataGatewayTest {

    private TimeEntryDataGateway gateway = new TimeEntryDataGateway();


    @Test
    public void testCreate() {
        TimeEntryFields fields = timeEntryFieldsBuilder()
                .projectId(22L)
                .userId(12L)
                .date(LocalDate.parse("2016-02-28"))
                .hours(8)
                .build();
        TimeEntryRecord created = gateway.create(fields);


        assertThat(created.id).isNotNull();
        assertThat(created.projectId).isEqualTo(22L);
        assertThat(created.userId).isEqualTo(12L);
        assertThat(created.date).isEqualTo(LocalDate.parse("2016-02-28"));
        assertThat(created.hours).isEqualTo(8);

        TimeEntryRecord found = gateway.find(created.id);

        assertThat(found.projectId).isEqualTo(22L);
        assertThat(found.userId).isEqualTo(12L);
        assertThat(found.date).isEqualTo(LocalDate.parse("2016-02-28"));
        assertThat(found.hours).isEqualTo(8);
    }

    @Test
    public void testFindAllByUserId() {
        TimeEntryRecord created = gateway.create(timeEntryFieldsBuilder()
                .projectId(22L)
                .userId(12L)
                .date(LocalDate.parse("2016-02-28"))
                .hours(8)
                .build());

        gateway.create(timeEntryFieldsBuilder()
                .projectId(999L)
                .userId(999L)
                .date(LocalDate.parse("2016-02-28"))
                .hours(8)
                .build());


        List<TimeEntryRecord> result = gateway.findAllByUserId(12L);


        assertThat(result).containsExactlyInAnyOrder(created);
    }
}
