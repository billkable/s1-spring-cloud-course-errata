package io.pivotal.pal.tracker.timesheets;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.client.RestOperations;

import java.net.SocketTimeoutException;

public class ProjectClient {
    private final RestOperations restOperations;
    private final String endpoint;
    private final ProjectClientCache projectClientCache;

    public ProjectClient(RestOperations restOperations,
                         String registrationServerEndpoint,
                         ProjectClientCache projectClientCache) {
        this.restOperations = restOperations;
        this.endpoint = registrationServerEndpoint;
        this.projectClientCache = projectClientCache;
    }

    @HystrixCommand(commandKey = "getProject",
                    fallbackMethod = "getProjectFromCache")
    public ProjectInfo getProject(long projectId) {
        ProjectInfo projectInfo = restOperations.getForObject(endpoint + "/projects/" + projectId, ProjectInfo.class);

        projectClientCache.put(projectId,projectInfo);

        return projectInfo;
    }

    @HystrixCommand(commandKey = "getProjectFromCache")
    public ProjectInfo getProjectFromCache(long projectId) {
        return projectClientCache.get(projectId);
    }

}
