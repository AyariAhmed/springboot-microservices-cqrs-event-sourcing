package com.cqrs.command;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CommandMicroserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommandMicroserviceApplication.class, args);
    }
}
