package com.bassem.springbootquiz;

import com.bassem.springbootquiz.salemanagment.dto.ClientRequestDto;
import com.bassem.springbootquiz.salemanagment.entities.Client;
import com.bassem.springbootquiz.salemanagment.repositories.ClientRepository;
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

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Bassem Al Mahow
 */
@TestBootsrap
class ClientControllerTest {
    /**
     * Object Parameters
     */
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ClientRepository clientRepository;
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void testViewNotFound() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.get("/api/clients/5")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()));
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void testView() throws Exception {
        Client client = new Client();
        client.setName("Bassem").setLastName("Mahow").setMobile("0943424092");
        client = clientRepository.save(client);
        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/clients/" + client.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue("Bassem".equals(node.get("name").asText()));
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void testViewAll() throws Exception {
        Client client = new Client();
        client.setName("Bassem").setLastName("Mahow").setMobile("0943424092");
        client = clientRepository.save(client);
        Client client2 = new Client();
        client2.setName("Khaled").setLastName("Zed").setMobile("0996545834");
        client2 = clientRepository.save(client2);


        MvcResult result = mvc.perform(
                MockMvcRequestBuilders.get("/api/clients/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();
        JsonNode node = objectMapper.readTree(result.getResponse().getContentAsString());
        Assertions.assertTrue(node.isArray());

    }
    //----------------------------------------------------------------------------------------------------------------------------


    /**
     *
     * @throws Exception
     */
    @Test
    void testCreate() throws Exception {
        ClientRequestDto dto = new ClientRequestDto();
        {
            dto.name = "Soso";
            dto.mobile = "0993459987";
            dto.lastName = "Gahed";
        }


        MvcResult result= mvc.perform(
                MockMvcRequestBuilders.post("/api/clients/")
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andReturn();

        String location= result.getResponse().getHeader("Location");
        Long idFromLocation = Long.parseLong(location.substring(location.lastIndexOf("/") + 1));
        Optional<Client> createdClient = clientRepository.findById(idFromLocation);
        Assertions.assertTrue(createdClient.isPresent());
        if(createdClient.isPresent()) {
            Assertions.assertTrue(createdClient.get().getName().equals("Soso"));
        }
    }
    //----------------------------------------------------------------------------------------------------------------------------

    /**
     *
     * @throws Exception
     */
    @Test
    void testEdit() throws Exception {
        Client client = new Client();
        client.setName("Wared").setLastName("radi").setMobile("0996546432");
        client = clientRepository.save(client);

        ClientRequestDto dto = new ClientRequestDto();
        {
            dto.name = "Tahsen";
            dto.mobile = "0993454459";
            dto.lastName = "Ali";
        }


        MvcResult result= mvc.perform(
                MockMvcRequestBuilders.put("/api/clients/"+client.getId())
                        .content(objectMapper.writeValueAsString(dto))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();



        Optional<Client> updatedClient = clientRepository.findById(client.getId());
        Assertions.assertTrue(updatedClient.isPresent());
        if(updatedClient.isPresent()) {
            Assertions.assertTrue(updatedClient.get().getName().equals("Tahsen"));
        }
    }
}