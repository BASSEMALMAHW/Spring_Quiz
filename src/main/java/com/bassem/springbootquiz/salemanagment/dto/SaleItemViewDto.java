package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.salemanagment.entities.SaleItem;

import java.math.BigDecimal;

/**
 * @author Bassem Al Mahow
 */

public class SaleItemViewDto {
    /**
     * Parameters
     */
    public Long id;
    public Integer quantity;
    public BigDecimal price;
    /**
     * Object Parameters
     */
    public ProductViewDto product;
    //--------------------------------------------------

    /**
     *
     * @param saleItem
     */
    public SaleItemViewDto(SaleItem saleItem) {
        this.id       =saleItem.getId();
        this.quantity =saleItem.getQuantity();
        this.price    =saleItem.getPrice();
        this.product  =new ProductViewDto(saleItem.getProduct());
    }
}
