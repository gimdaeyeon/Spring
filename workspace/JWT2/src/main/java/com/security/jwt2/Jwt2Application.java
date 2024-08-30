package com.security.jwt2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class Jwt2Application {

    public static void main(String[] args) {
        SpringApplication.run(Jwt2Application.class, args);
    }

}
