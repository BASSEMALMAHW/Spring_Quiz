package com.bassem.springbootquiz.salemanagment.services;


import com.bassem.springbootquiz.container.exception.ManagedException;
import com.bassem.springbootquiz.container.exception.SaleManagementError;
import com.bassem.springbootquiz.salemanagment.dto.ClientRequestDto;
import com.bassem.springbootquiz.salemanagment.dto.ClientViewDto;
import com.bassem.springbootquiz.salemanagment.entities.Client;
import com.bassem.springbootquiz.salemanagment.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bassem Al Mahow
 */

@RequiredArgsConstructor
@Service
public class ClientService {
    /**
     * Object Parameters
     */
    private final ClientRepository clientRepository;
    //-------------------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @return
     */
    public ClientViewDto view(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ManagedException(SaleManagementError.NOT_FOUND, "Client Not Exist"));
        return new ClientViewDto(client);
    }
    //-------------------------------------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public List<ClientViewDto> viewAll() {
        return clientRepository.findAll().stream()
                .map(e -> new ClientViewDto(e))
                .collect(Collectors.toList());
    }
    //-------------------------------------------------------------------------------------------------------

    /**
     *
     * @param clientRequestDto
     * @return
     */
    public Client create(ClientRequestDto clientRequestDto) {
        Client p = new Client()
                .setName(clientRequestDto.name)
                .setLastName(clientRequestDto.lastName)
                .setMobile(clientRequestDto.mobile);
        return clientRepository.save(p);
    }
    //-------------------------------------------------------------------------------------------------------

    /**
     *
     * @param id
     * @param clientRequestDto
     * @return
     */
    public Client update(Long id, ClientRequestDto clientRequestDto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ManagedException(SaleManagementError.NOT_FOUND, "Client Not Exist"));
        client.setName(clientRequestDto.name)
                .setMobile(clientRequestDto.mobile)
                .setLastName(clientRequestDto.lastName);
        return clientRepository.save(client);
    }


}
