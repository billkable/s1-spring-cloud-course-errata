package io.pivotal.pal.tracker.hystrixdashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@SpringBootApplication
public class HystrixDashboardApp {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardApp.class, args);
    }
}
