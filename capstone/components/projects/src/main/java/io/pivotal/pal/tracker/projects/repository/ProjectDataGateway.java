package io.pivotal.pal.tracker.projects.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProjectDataGateway {

    private final Map<Long, ProjectRecord> db = new HashMap<>();
    private long currentId = 0;

    public ProjectRecord create(ProjectFields fields) {
        long id = ++currentId;

        ProjectRecord record = ProjectRecord.projectRecordBuilder().accountId(fields.accountId).id(id).active(true).name(fields.name).build();

        db.put(id, record);

        return record;
    }

    public List<ProjectRecord> findAllByAccountId(Long accountId) {
        return db.values().stream().filter(r -> r.accountId == accountId).collect(Collectors.toList());
    }

    public ProjectRecord find(long id) {
        return db.get(id);
    }

}
