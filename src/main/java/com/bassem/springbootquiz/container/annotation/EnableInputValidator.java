package com.bassem.springbootquiz.container.annotation;


import com.bassem.springbootquiz.container.config.InputValidatorConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Bassem Al Mahow
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({InputValidatorConfiguration.class})
public @interface EnableInputValidator {
}
