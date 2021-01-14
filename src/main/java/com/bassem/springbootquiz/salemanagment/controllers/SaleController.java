package com.bassem.springbootquiz.salemanagment.controllers;

import com.bassem.springbootquiz.salemanagment.dto.SaleRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.SaleViewDto;
import com.bassem.springbootquiz.salemanagment.entities.Sale;
import com.bassem.springbootquiz.salemanagment.services.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;

/**
 * @author Bassem Al Mahow
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/sales")
public class SaleController {
    /**
     * Parameters
     */
    private final SaleService saleService;
   //------------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity fetch(@PathVariable Long id) {
        SaleViewDto saleViewDto = saleService.get(id);
        return ResponseEntity.ok(saleViewDto);
    }
    //------------------------------------------------------------------------------------------------


    /**
     *
     * @param saleRequestDto
     * @return
     */
    @PostMapping(path = "/")
    public ResponseEntity create(@RequestBody SaleRequestDto saleRequestDto) {
        Sale sale = saleService.create(saleRequestDto);
        return ResponseEntity.created(URI.create("/api/sales/" + sale.getId())).body(null);
    }

   //------------------------------------------------------------------------------------------------    }

    /**
     *
     * @param id
     * @param saleRequestDto
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity edit(@PathVariable Long id, @RequestBody SaleRequestDto saleRequestDto) {
        Sale sale = saleService.update(id, saleRequestDto);
        return ResponseEntity.ok().build();
    }

}
