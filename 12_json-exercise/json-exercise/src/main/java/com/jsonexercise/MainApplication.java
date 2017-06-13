package com.jsonexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MainApplication {
    public static void main(String[] args) {
//        ANSRBuilder.build(BuilderStrategy.REPOSITORY_AND_SERVICES);
        SpringApplication.run(MainApplication.class, args);
    }
}
