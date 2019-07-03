package com.lwj.project;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ProjectAmpqApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectAmpqApplication.class, args);
    }

}
