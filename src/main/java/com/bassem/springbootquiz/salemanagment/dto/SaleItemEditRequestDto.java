package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.container.dto.BaseRequestDTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * @author Bassem Al Mahow
 */

public class SaleItemEditRequestDto extends BaseRequestDTO {
    /**
     * Parameters
     */
    @NotNull
    //@Positive
    public Integer quantity;
    //@Positive
    @NotNull
    public BigDecimal price;

}
