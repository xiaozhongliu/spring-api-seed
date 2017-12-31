package com.webapi.seed;

import com.webapi.seed.config.PackageProps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner init(PackageProps config) {
        return evt -> System.out.println("================ App [" + config.getName() + "] Started ================");
    }

}
