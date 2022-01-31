package com.cqrs.query;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class QueryMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueryMicroserviceApplication.class, args);
    }

}
