package com.example.controller;

import com.example.dto.ClientDTO;
import com.example.Exception.ClientException;
import com.example.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class ClientController {

    @Autowired
    private IClientService iClientService;

    @PostMapping("/save")
    public ResponseEntity<ClientDTO> saveClient(@RequestBody ClientDTO clientDTO) throws ClientException {
        ClientDTO clientCreate = iClientService.saveClient(clientDTO);
        return new ResponseEntity<>(clientCreate, HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public ResponseEntity<List<ClientDTO>> listClients() {
        List<ClientDTO> clients = iClientService.listClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }


    @GetMapping("/{sharedKey}")
    public ResponseEntity<ClientDTO> findBySharedKey(@PathVariable String sharedKey) throws ClientException {
        ClientDTO clientFind = iClientService.findBySharedKey(sharedKey);
        return new ResponseEntity<>(clientFind, HttpStatus.FOUND);
    }
}
