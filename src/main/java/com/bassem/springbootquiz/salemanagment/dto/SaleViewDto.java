package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.salemanagment.entities.Sale;
import javafx.scene.transform.Scale;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bassem Al Mahow
 */

public class SaleViewDto {
    /**
     * Parameters
     */
    public Long id;
    public Instant creationDate;
    public BigDecimal total;
    public String seller;
    /**
     * Object Parameters
     */
    public ClientViewDto client;
    public List<SaleItemViewDto> saleItems = new ArrayList<>();
    //---------------------------------------------------------------------
    /**
     *
     * @param sale
     */
    public SaleViewDto(Sale sale) {
        this.id = sale.getId();
        this.creationDate = sale.getCreationDate();
        this.total = sale.getItems().stream()
                .peek(i -> saleItems.add(new SaleItemViewDto(i)))
                .map(i -> i.getPrice().multiply(new BigDecimal(i.getQuantity())))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);

        this.seller = sale.getSeller().getName();
        this.client =  new ClientViewDto(sale.getClient());
    }

}
