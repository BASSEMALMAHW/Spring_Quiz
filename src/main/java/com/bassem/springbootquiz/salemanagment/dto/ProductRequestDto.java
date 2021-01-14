package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.container.dto.BaseRequestDTO;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Bassem Al Mahow
 */
public class ProductRequestDto extends BaseRequestDTO {
    /**
     * Parameters
     */
    @NotBlank
    public String name;
    public String description;
    @NotNull
    public Long categoryId;
}
