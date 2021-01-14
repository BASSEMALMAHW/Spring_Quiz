package com.bassem.springbootquiz.salemanagment.controllers;



import com.bassem.springbootquiz.salemanagment.dto.ProductRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.ProductViewDto;
import com.bassem.springbootquiz.salemanagment.entities.Product;
import com.bassem.springbootquiz.salemanagment.services.ProductService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Bassem Al Mahow
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/products")
public class ProductController {
    /**
     * Parameters
     */
    private final ProductService productService;

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> fetchById(@PathVariable Long id) throws NotFoundException {
        ProductViewDto dto = productService.view(id);
        return ResponseEntity.ok(dto);
    }
    //---------------------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    @GetMapping(path = "/")
    public ResponseEntity<?> fetchAll() {
        List<ProductViewDto> dtos = productService.viewAll();
        return ResponseEntity.ok(dtos);
    }
    //---------------------------------------------------------------------------------------------

    /**
     *
     * @param productRequestDto
     * @return
     */
    @PostMapping(path = "/")
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productRequestDto) {
        Product p = productService.create(productRequestDto);
        return ResponseEntity.created(URI.create("/api/products/" + p.getId())).build();
    }
    //---------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @param productRequestDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody ProductRequestDto productRequestDto) {
        Product p = productService.update(id, productRequestDto);
        return ResponseEntity.ok().build();
    }


}
