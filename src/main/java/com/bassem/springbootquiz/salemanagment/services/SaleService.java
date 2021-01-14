package com.bassem.springbootquiz.salemanagment.services;


import com.bassem.springbootquiz.salemanagment.dto.SaleRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.SaleViewDto;
import com.bassem.springbootquiz.salemanagment.entities.Sale;
import com.bassem.springbootquiz.salemanagment.repositories.ClientRepository;
import com.bassem.springbootquiz.salemanagment.repositories.SaleRepository;
import com.bassem.springbootquiz.salemanagment.repositories.SellerRepository;
import com.bassem.springbootquiz.container.exception.SaleManagementError;
import com.bassem.springbootquiz.container.exception.ManagedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.Instant;

/**
 * @author Bassem Al Mahow
 */


@Service
@RequiredArgsConstructor
public class SaleService {

    /**
     * Object Parameters
     */
    private final SaleRepository saleRepository;
    private final ClientRepository clientRepository;
    private final SellerRepository sellerRepository;
    //---------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @return
     */
    public SaleViewDto get(Long id) {
        Sale sale = saleRepository.findByIdFetchItemsAndClient(id)
                .orElseThrow(() -> new ManagedException(SaleManagementError.NOT_FOUND, "Sale Not Exist"));
        SaleViewDto dto = new SaleViewDto(sale);
        return dto;
    }
    //---------------------------------------------------------------------------------------

    /**
     *
     * @param saleRequestDto
     * @return
     */
    public Sale create(SaleRequestDto saleRequestDto) {
        validateClinet(saleRequestDto.clientId);
        validateSeller(saleRequestDto.sellerId);

        Sale sale = new Sale().setSeller(sellerRepository.getOne(saleRequestDto.sellerId) )
                           .setCreationDate(Instant.now())
                           .setClient(clientRepository.getOne(saleRequestDto.clientId))
                           .setCreationDate(Instant.now());

        return saleRepository.save(sale);
    }
    //---------------------------------------------------------------------------------------


    /**
     *
     * @param id
     * @param saleRequestDto
     * @return
     */
    public Sale update(Long id, SaleRequestDto saleRequestDto) {
        Sale sale = validateIdAndGetSale(id);
        validateClinet(saleRequestDto.clientId);
        validateSeller(saleRequestDto.sellerId);
        sale.setClient(clientRepository.getOne(saleRequestDto.clientId))
                .setSeller(sellerRepository.getOne(saleRequestDto.sellerId));
        return saleRepository.save(sale);
    }
    //---------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @return
     */
    private Sale validateIdAndGetSale(Long id) {
        Sale sale = saleRepository.getOne(id);
        if (sale == null) {
            throw new ManagedException(SaleManagementError.NOT_FOUND, "Sale Not Exist");
        }
        return sale;
    }
    //---------------------------------------------------------------------------------------

    /**
     *
     * @param clientId
     */
    private void validateClinet(Long clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new ManagedException(SaleManagementError.NOT_FOUND, "Client Not Exist");
        }
    }
    //---------------------------------------------------------------------------------------

    /**
     *
     * @param sellerId
     */
    private void validateSeller(Long sellerId) {
        if (!sellerRepository.existsById(sellerId)) {
            throw new ManagedException(SaleManagementError.NOT_FOUND, "Seller Not Exist");
        }
    }

}
