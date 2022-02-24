package com.example.slack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing
@SpringBootApplication
public class SlackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlackApplication.class, args);
    }

}
