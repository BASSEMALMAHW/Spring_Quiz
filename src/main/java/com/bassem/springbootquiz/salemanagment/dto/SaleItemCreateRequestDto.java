package com.bassem.springbootquiz.salemanagment.dto;

import javax.validation.constraints.NotNull;
/**
 * @author Bassem Al Mahow
 */

public class SaleItemCreateRequestDto extends SaleItemEditRequestDto {
    /**
     * Parameters
     */
    @NotNull
    public Long saleId;

    @NotNull
    public Long productId;


}
