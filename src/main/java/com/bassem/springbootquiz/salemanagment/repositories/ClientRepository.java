package com.bassem.springbootquiz.salemanagment.repositories;

import com.bassem.springbootquiz.salemanagment.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Bassem Al Mahow
 */
public interface ClientRepository extends JpaRepository<Client, Long> {
}
