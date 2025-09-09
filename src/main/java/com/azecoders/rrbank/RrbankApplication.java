package com.azecoders.rrbank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RrbankApplication {

    public static void main(String[] args) {
        SpringApplication.run(RrbankApplication.class, args);
    }


}
