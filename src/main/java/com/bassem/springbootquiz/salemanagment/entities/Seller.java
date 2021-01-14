package com.bassem.springbootquiz.salemanagment.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Bassem Al Mahow
 */

/**
 * Category of the product
 */
@Getter
@Setter
@Accessors(chain = true)
@Entity
public class Seller  extends Versioned{
    /**
     * Parameters
     */
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false,unique = true)
    private String name;
}
