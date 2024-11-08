package com.pblgllgs.sb3mcsvcjob;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class Sb3McsvcJobApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sb3McsvcJobApplication.class, args);
    }

}
