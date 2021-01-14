package com.bassem.springbootquiz;

import com.bassem.springbootquiz.salemanagment.dto.SaleRequestDto;
import com.bassem.springbootquiz.salemanagment.entities.*;
import com.bassem.springbootquiz.salemanagment.repositories.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bassem Al Mahow
 */

@TestBootsrap
class SaleControllerTest {
    /**
     * Object Parameters
     */
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private SaleRepository saleRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SaleItemRepository saleItemRepository;
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void get() throws Exception {

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

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/sales/" + sale.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue("Waleed".equals(node.get("seller").asText()));

    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void getWithSaleItem() throws Exception {
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

        em.flush();
        em.clear();

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/sales/" + sale.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue("Waleed".equals(node.get("seller").asText()));
        Assertions.assertTrue(1000 == node.get("total").asInt());

    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void create() throws Exception {
        Client client = new Client();
        client.setName("ali").setLastName("sameh").setMobile("0999657444");
        client = clientRepository.save(client);

        Seller seller = new Seller();
        seller.setName("Waleed");
        seller =  sellerRepository.save(seller);

        SaleRequestDto dto = new SaleRequestDto();
        {
            dto.sellerId = seller.getId();
            dto.clientId = client.getId();
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/api//sales/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String s = result.getResponse().getHeader("Location");
        Assertions.assertNotNull(s);
        Long recordId = Long.parseLong(s.substring(s.lastIndexOf("/") + 1));
        Sale p = em.find(Sale.class, recordId);
        Assertions.assertNotNull(p);

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
        client.setName("ali").setLastName("sameh").setMobile("0999657444");
        client = clientRepository.save(client);

        Seller seller = new Seller();
        seller.setName("Waleed");
        seller =  sellerRepository.save(seller);

        Sale sale = new Sale();
        sale.setSeller(seller)
                .setCreationDate(Instant.now())
                .setClient(client);
        sale = saleRepository.save(sale);

        SaleRequestDto dto = new SaleRequestDto();
        {
            dto.sellerId = seller.getId();
            dto.clientId = client.getId();
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/api/sales/" + sale.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        Sale updatedSale = this.saleRepository.getOne(sale.getId());

        Assertions.assertTrue(updatedSale.getSeller().getName().equals("Waleed"));

    }
}