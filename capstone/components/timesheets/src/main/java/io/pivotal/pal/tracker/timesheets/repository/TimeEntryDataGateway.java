package io.pivotal.pal.tracker.timesheets.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class TimeEntryDataGateway {

    private long currentId = 0;
    private final Map<Long, TimeEntryRecord> db = new HashMap<>();

    public TimeEntryRecord create(TimeEntryFields fields) {
        long id = ++currentId;

        TimeEntryRecord created = TimeEntryRecord.timeEntryRecordBuilder()
                .id(id)
                .projectId(fields.projectId)
                .userId(fields.userId)
                .date(fields.date)
                .hours(fields.hours)
                .build();

        db.put(id, created);

        return created;
    }

    public List<TimeEntryRecord> findAllByUserId(long userId) {
        return db.values().stream().filter(r -> r.userId == userId).collect(Collectors.toList());
    }

    public TimeEntryRecord find(long id) {
        return db.get(id);
    }

}
