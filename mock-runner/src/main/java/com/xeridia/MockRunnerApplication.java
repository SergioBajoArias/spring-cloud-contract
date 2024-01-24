package com.xeridia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;

@SpringBootApplication
@EnableStubRunnerServer
public class MockRunnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockRunnerApplication.class, args);;
    }
}