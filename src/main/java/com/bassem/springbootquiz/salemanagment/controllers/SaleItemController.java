package com.bassem.springbootquiz.salemanagment.controllers;

import com.bassem.springbootquiz.salemanagment.dto.SaleItemCreateRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.SaleItemEditRequestDto;
import com.bassem.springbootquiz.salemanagment.entities.SaleItem;
import com.bassem.springbootquiz.salemanagment.services.SaleItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

/**
 * @author Bassem Al Mahow
 */

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/saleitems")
public class SaleItemController {
    /**
     * Object Parameters
     */
    private final SaleItemService saleItemService;
    //-----------------------------------------------------------------------------------------

    @PostMapping(path = "/")
    public ResponseEntity create(@RequestBody SaleItemCreateRequestDto requestDto) {
        SaleItem c = saleItemService.create(requestDto);
        return ResponseEntity.created(URI.create("/api/clients/" + c.getId())).build();
    }
    //-----------------------------------------------------------------------------------------

    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id,
                               @RequestBody SaleItemEditRequestDto requestDto) {
        SaleItem p = saleItemService.update(id, requestDto);
        return ResponseEntity.ok().build();
    }
    //-----------------------------------------------------------------------------------------
}
