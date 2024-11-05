package com.pblgllgs.sb3mcsvcjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Sb3McsvcJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sb3McsvcJobApplication.class, args);
    }

}
