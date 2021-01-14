package com.bassem.springbootquiz.salemanagment.repositories;


import com.bassem.springbootquiz.salemanagment.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bassem Al Mahow
 */

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
