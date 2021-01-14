package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.container.dto.BaseRequestDTO;
import javax.persistence.Column;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Bassem Al Mahow
 */
public class ClientRequestDto extends BaseRequestDTO {
    /**
     * Parameters
     */
    @Column(nullable = false, unique = true)
    public String name;
    @NotBlank
    public String lastName;
    @NotBlank
    public String mobile;
}
