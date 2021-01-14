package com.bassem.springbootquiz;

import com.bassem.springbootquiz.container.annotation.EnableInputValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableInputValidator
@SpringBootApplication
public class SpringBootQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootQuizApplication.class, args);
    }

}
