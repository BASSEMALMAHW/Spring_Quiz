package com.bassem.springbootquiz.salemanagment.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Bassem Al Mahow
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Sale  extends Versioned {
    /**
     * Parameters
     */
    @Id
    @GeneratedValue
    private Long id;

    private Instant creationDate;

    //---------------------------------------------------
    /**
     * Object Parameters
     */
    //the client who brought the Sale
    @ManyToOne(fetch = FetchType.LAZY)
    private Client client;

    //the seller who SOLD the Sale
    @ManyToOne(fetch = FetchType.LAZY)
    private Seller seller;

    //List of items represents all the Products in the Sale
    @OneToMany(mappedBy = "sale" )
    private List<SaleItem> items = new ArrayList<>();


    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Sale{" +
                "  id           =" + id +
                ", creationDate =" + creationDate +
                ", client.id    =" + client.getId() +
                ", seller       ='" + seller.getId() + '\'' +
                '}';
    }
}
