package com.bassem.springbootquiz.container.exception;

import lombok.Getter;

/**
 * @author Bassem Al Mahow
 */

public class ManagedException extends RuntimeException {
    @Getter
    private final SaleManagementError error;

    public ManagedException(SaleManagementError error, String message) {
        super(message);
        this.error = error;
    }
}
