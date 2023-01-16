package com.example.demo;

import com.example.demo.util.IdWorker;
import com.neuqsoft.commons.spring.exception.handler.EnableGlobalException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableGlobalException
public class Demo3Application {

    @Bean
    IdWorker idWorker() {
        return new IdWorker(1, 1);
    }
    public static void main(String[] args) {
        SpringApplication.run(Demo3Application.class, args);
    }

}
