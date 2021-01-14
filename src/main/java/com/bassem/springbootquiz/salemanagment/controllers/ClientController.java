package com.bassem.springbootquiz.salemanagment.controllers;


import com.bassem.springbootquiz.salemanagment.dto.ClientRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.ClientViewDto;
import com.bassem.springbootquiz.salemanagment.entities.Client;
import com.bassem.springbootquiz.salemanagment.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

/**
 * @author Bassem Al Mahow
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/clients")
public class ClientController {

    /**
     * Object Parameters
     */
    private final ClientService clientService;
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> fetch(@PathVariable Long id) {
        ClientViewDto dto = clientService.view(id);
        return ResponseEntity.ok(dto);
    }
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    @GetMapping(path = "/")
    public ResponseEntity<?> fetchAll() {
        List<ClientViewDto> dtos = clientService.viewAll();
        return ResponseEntity.ok(dtos);
    }
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @param clientRequestDto
     * @return
     */
    @PostMapping(path = "/")
    public ResponseEntity<?> create(@RequestBody ClientRequestDto clientRequestDto) {
        Client c = clientService.create(clientRequestDto);
        return ResponseEntity.created(URI.create("/api/clients/" + c.getId())).body(null);
    }
    //-----------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @param clientRequestDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody ClientRequestDto clientRequestDto) {
        Client p = clientService.update(id, clientRequestDto);
        return ResponseEntity.ok().build();
    }


}
