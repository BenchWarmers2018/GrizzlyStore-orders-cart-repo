package com.benchwarmers.grads.grizzlystoreorder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class GrizzlystoreOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrizzlystoreOrderApplication.class, args);

        System.out.println("Avi in Transaction repo....");
    }
}
