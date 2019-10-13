package io.pivotal.pal.tracker.turbine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.stream.EnableTurbineStream;

@EnableTurbineStream
@SpringBootApplication
public class TurbineServer {
    public static void main(String[] args) {
        SpringApplication.run(TurbineServer.class, args);
    }
}
