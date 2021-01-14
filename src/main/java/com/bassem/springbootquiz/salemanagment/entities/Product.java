package com.bassem.springbootquiz.salemanagment.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.time.Instant;

/**
 * @author Bassem Al Mahow
 */

@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Product extends Versioned {
    /**
     * Parameters
     */
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;
    //---------------------------------------------------

    /**
     * Object Parameters
     */

     //Date of creation
    @Column(nullable = false)
    private Instant creationDate;

    //Category of the product
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Category category;


}
