package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.salemanagment.entities.Product;
import java.time.Instant;

/**
 * @author Bassem Al Mahow
 */

public class ProductViewDto {
    /**
     * Parameters
     */
    public Long id;
    public String name;
    public String description;
    public String category;
    public Instant creationDate;
//-------------------------------------------------

    /**
     *
     * @param product
     */
    public ProductViewDto(Product product) {
        id           = product.getId();
        name         = product.getName();
        description  = product.getDescription();
        category     = product.getCategory().getName();
        creationDate = product.getCreationDate();
    }
}
