package com.xeridia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.contract.stubrunner.server.EnableStubRunnerServer;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;

@SpringBootApplication
@EnableStubRunnerServer
// @AutoConfigureStubRunner(stubsMode = StubRunnerProperties.StubsMode.LOCAL, ids = "com.xeridia:producer:+:stubs:8081")
public class MockRunnerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MockRunnerApplication.class, args);;
    }
}