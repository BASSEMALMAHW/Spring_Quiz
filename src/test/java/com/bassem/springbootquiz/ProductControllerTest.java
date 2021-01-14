package com.bassem.springbootquiz;

import com.bassem.springbootquiz.salemanagment.dto.ProductRequestDto;
import com.bassem.springbootquiz.salemanagment.entities.Category;
import com.bassem.springbootquiz.salemanagment.entities.Product;
import com.bassem.springbootquiz.salemanagment.repositories.CategoryRepository;
import com.bassem.springbootquiz.salemanagment.repositories.ProductRepository;
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

import java.time.Instant;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Bassem Al Mahow
 */

@TestBootsrap
public class ProductControllerTest {
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
    private ProductRepository productRepository;
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    public void testCreateCategoryNotFound() throws Exception {
        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.name = "Crest";
            dto.description = "Crested - toothpaste, which is one of the most effective methods of pursuing a snow white smile.";
            dto.categoryId = 1L;
        }

        mvc.perform(
                MockMvcRequestBuilders.post("/api/products/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    public void testCreateBadInput() throws Exception {
        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.description = "Crested - toothpaste, which is one of the most effective methods of pursuing a snow white smile.";
            dto.categoryId = 1L;
        }

        mvc.perform(
                MockMvcRequestBuilders.post("/api/products/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    public void testcCreate() throws Exception {
        Category category = CreateCategory();

        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.name = "Crest";
            dto.description = "Crested - toothpaste, which is one of the most effective methods of pursuing a snow white smile.";
            dto.categoryId = category.getId();
        }

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.post("/api/products/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String location = result.getResponse().getHeader("Location");
        Long idFromLocation = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));
        Optional<Product> p = productRepository.findById(idFromLocation);
        Assertions.assertTrue(p.isPresent());
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    public void testEdit() throws Exception {
        Category category = CreateCategory();
        Product product = new Product();
        product.setName("Laptop")
                .setCreationDate(Instant.now())
                .setDescription("Laptop is for help people ")
                .setCategory(category);
        productRepository.save(product);

        ProductRequestDto dto = new ProductRequestDto();
        {
            dto.name = "Wristwatch";
            dto.description = "A watch is a portable timepiece intended to be carried or worn by a person. " +
                    "It is designed to keep a consistent movement despite the motions caused by the person's activities";
            dto.categoryId = category.getId();
        }


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.put("/api/products/" + product.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Optional<Product> p = productRepository.findById(product.getId());
        Assertions.assertTrue(p.isPresent());
        Assertions.assertTrue(p.get().getName().equals("Wristwatch"));
    }

    private Category CreateCategory() {
        Category category = new Category();
        category.setName("Category_1");
        category = this.categoryRepository.save(category);
        return category;
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGet() throws Exception {
        Category category = CreateCategory();
        Product product = new Product();
        product.setName("Car")
                .setCreationDate(Instant.now())
                .setDescription("A car use to transfer the people from place to author")
                .setCategory(category);

        productRepository.save(product);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/products/" + product.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andReturn();

        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Optional<Product> p = productRepository.findById(node.get("id").asLong());
        Assertions.assertTrue(p.isPresent());
        Assertions.assertTrue(p.get().getName().equals("Car"));

    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    public void testGetAll() throws Exception {
        Category category = CreateCategory();

        Product product = new Product();
        product.setName("Phone")
                .setCreationDate(Instant.now())
                .setDescription("Phone use for communicate with us")
                .setCategory(category);
        productRepository.save(product);

        Product product2 = new Product();
        product2.setName("pen")
                .setCreationDate(Instant.now())
                .setDescription("pen use to write any thing")
                .setCategory(category);
        productRepository.save(product2);

        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/products/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());

        Assertions.assertTrue(node.isArray());
        Assertions.assertNull(node.get(3));
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */

    @Test
    public void testGetNotFound() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/products/2")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }

}
