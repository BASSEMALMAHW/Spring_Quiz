package com.bassem.springbootquiz.salemanagment.repositories;

import com.bassem.springbootquiz.salemanagment.entities.SaleItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bassem Al Mahow
 */
public interface SaleItemRepository extends JpaRepository<SaleItem, Long> {
}
