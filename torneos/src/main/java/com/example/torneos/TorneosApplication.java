package com.example.torneos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This annotation marks the class as the main entry point for Spring Boot application.
// It enables component scanning, auto-configuration, and configuration support.
@SpringBootApplication
public class TorneosApplication {

    // The main method launches the Spring Boot application.
    public static void main(String[] args) {
        SpringApplication.run(TorneosApplication.class, args);
    }
}
