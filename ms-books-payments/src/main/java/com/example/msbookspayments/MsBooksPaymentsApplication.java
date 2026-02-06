package com.example.msbookspayments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.example.msbookspayments")
@EnableJpaRepositories(basePackages = "com.example.msbookspayments")
public class MsBooksPaymentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsBooksPaymentsApplication.class, args);
    }

}
