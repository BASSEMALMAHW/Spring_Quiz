package com.bassem.springbootquiz.container.aspect;


import com.bassem.springbootquiz.container.dto.BaseRequestDTO;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * @author Bassem Al Mahow
 */

@Aspect
public class ControllerArgumentValidator {
    public ControllerArgumentValidator() {
    }

    @Around("within(@org.springframework.web.bind.annotation.RestController *))")
    public Object validate(ProceedingJoinPoint point) throws Throwable {
        Object[] args = point.getArgs();
        Object[] var3 = args;
        int var4 = args.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Object arg = var3[var5];
            if (arg instanceof BaseRequestDTO) {
                ((BaseRequestDTO)arg).validate();
            }
        }
        return point.proceed();
    }
}