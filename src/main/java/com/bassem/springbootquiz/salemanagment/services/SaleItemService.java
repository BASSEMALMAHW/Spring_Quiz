package com.bassem.springbootquiz.salemanagment.services;


import com.bassem.springbootquiz.salemanagment.dto.SaleItemCreateRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.SaleItemEditRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.SaleItemViewDto;
import com.bassem.springbootquiz.salemanagment.entities.Sale;
import com.bassem.springbootquiz.salemanagment.entities.SaleItem;
import com.bassem.springbootquiz.salemanagment.repositories.ProductRepository;
import com.bassem.springbootquiz.salemanagment.repositories.SaleItemRepository;
import com.bassem.springbootquiz.salemanagment.repositories.SaleRepository;
import com.bassem.springbootquiz.container.exception.SaleManagementError;
import com.bassem.springbootquiz.container.exception.ManagedException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Bassem Al Mahow
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class SaleItemService {
    /**
     * Object Parameters
     */
    private final SaleItemRepository saleItemRepository;
    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
//------------------------------------------------------------------------

    /**
     *
     * @param requestDto
     * @return
     */
    @Transactional
    public SaleItem create(SaleItemCreateRequestDto requestDto) {
        validateSale(requestDto.saleId);
        validateProduct(requestDto.productId);

        SaleItem saleItem = new SaleItem();
        Sale sale = saleRepository.getOne(requestDto.saleId);
        saleItem.setPrice(requestDto.price)
                .setQuantity(requestDto.quantity)
                .setSale(sale)
                .setProduct(productRepository.getOne(requestDto.productId));

        saleItem = this.saleItemRepository.save(saleItem);
        log.info("sale Item created: {}", saleItem);
        return saleItem;
    }
  //----------------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @param requestDto
     * @return
     */
    @Transactional
    public SaleItem update(Long id, SaleItemEditRequestDto requestDto) {
        SaleItem saleItem = saleItemRepository.findById(id)
                .orElseThrow(() -> new ManagedException(SaleManagementError.NOT_FOUND, "Sale Item Not Exist"));

        log.info("sale Item before update: {}", saleItem);

        saleItem.setPrice(requestDto.price)
                .setQuantity(requestDto.quantity);
        saleItem = this.saleItemRepository.save(saleItem);

        log.info("sale Item after update: {}", saleItem);

        return saleItem;
    }


    /**
     *
     * @param productId
     */
    private void validateProduct(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new ManagedException(SaleManagementError.NOT_FOUND, "Product Not Exist");
        }
    }
    //------------------------------------------------------------------------------------------------------

    /**
     *
     * @param saleId
     */
    private void validateSale(Long saleId) {
        if (!saleRepository.existsById(saleId)) {
            throw new ManagedException(SaleManagementError.NOT_FOUND, "Sale Not Exist");
        }
    }
    //------------------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @return
     * @throws NotFoundException
     */
    public SaleItemViewDto get(Long id) throws NotFoundException {
        SaleItem saleItem = this.saleItemRepository.findById(id)
                .orElseThrow(() -> new ManagedException(SaleManagementError.NOT_FOUND, "SaleItem not Exist"));
        return new SaleItemViewDto(saleItem);
    }
    //------------------------------------------------------------------------------------------------------

}
