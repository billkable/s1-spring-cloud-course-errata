package io.pivotal.pal.tracker.restsupport;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.composite.CompositeDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.web.client.RestOperations;

import java.time.Duration;
import java.util.List;

@Configuration
public class RestConfig {

    @LoadBalanced
    @Bean
    public RestOperations restOperations(RestOperationConfigProperties properties) {
        return new RestTemplateBuilder()
                .setReadTimeout(Duration.ofMillis(properties.getReadTimeoutMs()))
                .setConnectTimeout(Duration.ofMillis(properties.getConnectTimeoutMs()))
                .build();
    }

}
