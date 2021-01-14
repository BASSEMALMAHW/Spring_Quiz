package com.bassem.springbootquiz;

import com.bassem.springbootquiz.salemanagment.dto.SaleItemCreateRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.SaleItemEditRequestDto;
import com.bassem.springbootquiz.salemanagment.entities.*;
import com.bassem.springbootquiz.salemanagment.repositories.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bassem Al Mahow
 */
@TestBootsrap
class SaleItemControllerTest {
    /**
     * Object Parameters
     */
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SaleItemRepository saleItemRepository;
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void create() throws Exception {
        Category category = new Category();
        category.setName("Category_1");
        category = this.categoryRepository.save(category);

        Client client = new Client();
        client.setName("Bassem").setLastName("Mahow").setMobile("0943424092");
        client = clientRepository.save(client);

        Seller seller = new Seller();
        seller.setName("Waleed");
        seller =  sellerRepository.save(seller);

        Sale sale = new Sale();
        sale.setSeller(seller)
                .setCreationDate(Instant.now())
                .setClient(client);
        sale = saleRepository.save(sale);

        Product product2 = new Product();
        product2.setName("lazer")
                .setCreationDate(Instant.now())
                .setDescription("a game is used by children")
                .setCategory(category);
        product2 = productRepository.save(product2);

        SaleItemCreateRequestDto dto = new SaleItemCreateRequestDto();
        {
            dto.quantity = 6;
            dto.saleId=sale.getId();
            dto.price = new BigDecimal(1000);
            dto.productId=product2.getId();
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/api/saleitems/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String s = result.getResponse().getHeader("Location");
        Assertions.assertNotNull(s);
        Long recordId = Long.parseLong(s.substring(s.lastIndexOf("/") + 1));
        Optional<SaleItem> createdSaleItem = saleItemRepository.findById( recordId);
        Assertions.assertTrue(createdSaleItem.isPresent());

    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */

    @Test
    void edit() throws Exception {
        Category category = new Category();
        category.setName("Category_1");
        category = this.categoryRepository.save(category);

        Client client = new Client();
        client.setName("Bassem").setLastName("Mahow").setMobile("0943424092");
        client = clientRepository.save(client);

        Seller seller = new Seller();
        seller.setName("Waleed");
        seller =  sellerRepository.save(seller);

        Sale sale = new Sale();
        sale.setSeller(seller)
                .setCreationDate(Instant.now())
                .setClient(client);
        sale = saleRepository.save(sale);

        Product product3 = new Product();
        product3.setName("lazer")
                .setCreationDate(Instant.now())
                .setDescription("a game is used by children")
                .setCategory(category);
        product3 = productRepository.save(product3);

        SaleItem saleItem = new SaleItem();
        saleItem.setSale(sale)
                .setQuantity(2)
                .setPrice(new BigDecimal(500))
                .setProduct(product3);
        saleItemRepository.save(saleItem);

        SaleItemEditRequestDto dto = new SaleItemEditRequestDto();
        {
            dto.quantity = 13;
            dto.price = new BigDecimal(1700);
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/api/" +
                        "saleitems/"+saleItem.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        SaleItem updatedSaleItem = this.saleItemRepository.getOne(saleItem.getId());

        Assertions.assertTrue(updatedSaleItem.getQuantity().equals(13));

    }
}