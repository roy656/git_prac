package com.sparta.springteamassignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SpringteamassignmentApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringteamassignmentApplication.class, args);
    }

}
