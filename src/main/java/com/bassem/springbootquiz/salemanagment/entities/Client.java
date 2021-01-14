package com.bassem.springbootquiz.salemanagment.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Bassem Al Mahow
 */
@Setter
@Getter
@Accessors(chain = true)
@Entity
public class Client  extends Versioned{
    /**
     * Parameters
     */
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastName;

    private String mobile;


}
