package com.bassem.springbootquiz.salemanagment.services;


import com.bassem.springbootquiz.salemanagment.entities.Category;
import com.bassem.springbootquiz.salemanagment.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Bassem Al Mahow
 */

@RequiredArgsConstructor
@Service
public class CategoryService {
    /**
     * Object Parameters
     */
    private final CategoryRepository categoryRepository;
//--------------------------------------------------------------------------------
    /**
     *
     * @param id
     * @return
     */
    public Optional<Category> findOneById(Long id){
        return categoryRepository.findById(id);
    }

}
