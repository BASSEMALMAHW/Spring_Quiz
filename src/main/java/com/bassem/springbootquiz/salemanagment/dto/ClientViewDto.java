package com.bassem.springbootquiz.salemanagment.dto;


import com.bassem.springbootquiz.salemanagment.entities.Client;

/**
 * @author Bassem Al Mahow
 */

public class ClientViewDto {
    /**
     * Parameters
     */
    public Long id;

    public String name;

    public String lastName;

    public String mobile;
    //----------------------------------------------------

    /**
     *
     * @param client
     */
    public ClientViewDto(Client client) {
        this.id       = client.getId();
        this.name     = client.getName();
        this.lastName = client.getLastName();
        this.mobile   = client.getMobile();
    }
}
