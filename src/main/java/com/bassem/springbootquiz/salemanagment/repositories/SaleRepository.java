package com.bassem.springbootquiz.salemanagment.repositories;

import com.bassem.springbootquiz.salemanagment.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

/**
 * @author Bassem Al Mahow
 */

public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select s from Sale s " +
            " join fetch s.client " +
            " left join fetch s.items i " +
            " left join fetch i.product " +
            " where s.id=?1 ")
    Optional<Sale> findByIdFetchItemsAndClient(Long id);

    @Query("select s from Sale s " +
            " join fetch s.client " +
            " join fetch s.seller " +
            " left join fetch s.items i " +
            " left join fetch i.product " +
            " where s.id=?1 ")
    Optional<Sale> findByIdFetchItemsAndClientAndSeller(Long id);

}
