package com.example.service;

import com.example.dto.ClientDTO;
import com.example.Exception.ClientException;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IClientService {

    ClientDTO saveClient(ClientDTO clientDTO) throws ClientException;
    ClientDTO findBySharedKey(String sharedKey) throws ClientException;
    List<ClientDTO> listClients();
    Page<ClientDTO> findByFilter(ClientDTO clientDTO);

}
