package com.example.caseStudy.eCart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.util.Collections;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ECartApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ECartApplication.class);
        application.setDefaultProperties(Collections.singletonMap("server.port", "8081"));
        application.run(args);
    }

}
