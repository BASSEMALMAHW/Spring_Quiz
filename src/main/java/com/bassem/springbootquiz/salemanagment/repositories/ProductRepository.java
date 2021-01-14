package com.bassem.springbootquiz.salemanagment.repositories;

import com.bassem.springbootquiz.salemanagment.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author Bassem Al Mahow
 */
public interface ProductRepository extends JpaRepository<Product, Long> {

}
