package com.bassem.springbootquiz.container.config;


import com.bassem.springbootquiz.container.aspect.ControllerArgumentValidator;
import com.bassem.springbootquiz.container.handler.InvalidInputExceptionHandler;
import org.springframework.context.annotation.Bean;

/**
 * @author Bassem Al Mahow
 */
public class InputValidatorConfiguration {
    public InputValidatorConfiguration() {
    }

    @Bean
    public ControllerArgumentValidator controllerArgumentValidator() {
        return new ControllerArgumentValidator();
    }

    @Bean
    public InvalidInputExceptionHandler invalidInputExceptionHandler() {
        return new InvalidInputExceptionHandler() {};
    }
}

