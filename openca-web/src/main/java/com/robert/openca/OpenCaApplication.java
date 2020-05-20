package com.robert.openca;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class OpenCaApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenCaApplication.class, args);
    }

}
