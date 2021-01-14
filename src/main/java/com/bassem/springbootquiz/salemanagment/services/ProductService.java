package com.bassem.springbootquiz.salemanagment.services;


import com.bassem.springbootquiz.salemanagment.dto.ProductRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.ProductViewDto;
import com.bassem.springbootquiz.salemanagment.entities.Product;
import com.bassem.springbootquiz.salemanagment.repositories.CategoryRepository;
import com.bassem.springbootquiz.salemanagment.repositories.ProductRepository;
import com.bassem.springbootquiz.container.exception.SaleManagementError;
import com.bassem.springbootquiz.container.exception.ManagedException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

import static java.util.stream.Collectors.toList;


/**
 * @author Bassem Al Mahow
 */

@RequiredArgsConstructor
@Service
public class ProductService {

    /**
     * Object Parameters
     */
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    public ProductViewDto view(Long id) throws NotFoundException {
        Product product = productRepository.findById(id)
                .orElseThrow(()->new ManagedException(SaleManagementError.NOT_FOUND,"Product Not Exist"));
        return  new ProductViewDto(product);

    }
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public List<ProductViewDto> viewAll() {
        List<ProductViewDto> productViewDtos = productRepository.findAll().stream()
                .map(e->new ProductViewDto(e))
                .collect(toList());
        return productViewDtos;
    }
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @param productRequestDto
     * @return
     */
    public Product create(ProductRequestDto productRequestDto) {
        validateCategory(productRequestDto.categoryId);
        Product p = new Product()
                .setName(productRequestDto.name)
                .setCreationDate(Instant.now())
                .setDescription(productRequestDto.description)
                .setCategory(categoryRepository.getOne(productRequestDto.categoryId));

        return productRepository.save(p);
    }
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @param productRequestDto
     * @return
     */
    public Product update(Long id, ProductRequestDto productRequestDto) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ManagedException(SaleManagementError.NOT_FOUND, "Product Not Exist"));
        if (productRequestDto.categoryId != product.getCategory().getId().longValue())
            validateCategory(productRequestDto.categoryId);

        product.setName(productRequestDto.name)
                .setDescription(productRequestDto.description)
                .setCategory(categoryRepository.getOne(productRequestDto.categoryId));
        return productRepository.save(product);

    }
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @param categoryId
     */
    private void validateCategory(Long categoryId) {
        boolean exist = categoryRepository.existsById(categoryId);
        if (!exist)
            throw new ManagedException(SaleManagementError.NOT_FOUND, "Category Not Exist");
    }



}
