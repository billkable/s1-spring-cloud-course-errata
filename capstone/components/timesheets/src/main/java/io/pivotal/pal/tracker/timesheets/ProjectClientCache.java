package io.pivotal.pal.tracker.timesheets;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectClientCache {
    private final Map<Long, ProjectInfo> projectInfoMap;

    public ProjectClientCache() {
        projectInfoMap = new ConcurrentHashMap<>();
    }

    public boolean containsKey(long projectId) {
        return projectInfoMap.containsKey(projectId);
    }

    public ProjectInfo get(long projectId) {
        return projectInfoMap.get(projectId);
    }

    public void put(long projectId,
                    ProjectInfo projectInfo) {
        projectInfoMap.put(projectId, projectInfo);
    }
}
