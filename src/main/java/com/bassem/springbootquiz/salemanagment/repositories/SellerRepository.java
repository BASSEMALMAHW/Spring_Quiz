package com.bassem.springbootquiz.salemanagment.repositories;

import com.bassem.springbootquiz.salemanagment.entities.Seller;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bassem Al Mahow
 */
public interface SellerRepository extends JpaRepository<Seller,Long> {
}
