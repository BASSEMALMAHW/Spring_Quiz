package com.bassem.springbootquiz.container.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author Bassem Al Mahow
 */

@AllArgsConstructor
public enum SaleManagementError {

    NOT_FOUND(HttpStatus.NOT_FOUND);

    @Getter
    private HttpStatus status;
}
