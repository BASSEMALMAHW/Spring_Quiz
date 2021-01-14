package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.container.dto.BaseRequestDTO;

import javax.validation.constraints.NotNull;

/**
 * @author Bassem Al Mahow
 */

public class SaleRequestDto extends BaseRequestDTO {
    /**
     * Parameters
     */
    @NotNull
    public Long clientId;

    @NotNull
    public Long sellerId;
}
