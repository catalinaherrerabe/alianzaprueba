package com.example.service;

import com.example.dto.ClientDTO;
import com.example.Exception.ClientException;
import com.example.entities.ClientEntity;
import com.example.mapper.IClientMapper;
import com.example.repository.IClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class ClientService implements IClientService {
    @Autowired
    private IClientRepository iClientRepository;
    @Autowired
    private IClientMapper iClientMapper;

    @Override
    @Transactional
    public ClientDTO saveClient(ClientDTO clientDTO) throws ClientException {
        Optional<ClientEntity> optionalClient = iClientRepository.findBySharedKey(clientDTO.getSharedKey());
        ClientEntity client = optionalClient.orElseThrow(() -> new ClientException("400", "El cliente con el Shared KEY" + clientDTO.getSharedKey() + "ya existe en nuestra Base de datos."));
        if(validateEmail(clientDTO.getEmail())){
            return iClientMapper.toClient(iClientRepository.save(iClientMapper.fromClient(clientDTO)));
        }else{
            throw  new ClientException("200","El formato de email ingresado es inválido ");
        }

    }

    @Override
    public List<ClientDTO> listClients() {
        return iClientRepository.findAll().stream().map((ClientEntity client) -> iClientMapper.toClient(client)).collect(Collectors.toList());
    }

    @Override
    public Page<ClientDTO> findByFilter(ClientDTO clientDTO) {

        if (clientDTO != null) {
            if (clientDTO.getDateAdd() != null) {

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(clientDTO.getDateAdd());
                calendar.add(Calendar.HOUR, 23);
                clientDTO.setDateAdd(calendar.getTime());

            }
            return new PageImpl<>(iClientRepository.findByFilter(clientDTO.getSharedKey(), clientDTO.getBusinessId(),
                            clientDTO.getEmail(), clientDTO.getPhone(), clientDTO.getDateAdd()).stream()
                    .map((ClientEntity client) -> iClientMapper.toClient(client)).collect(Collectors.toList()));
        } else {
            return null;

        }

    }

    @Override
    public ClientDTO findBySharedKey(String sharedKey) throws ClientException {
        Optional<ClientEntity> optionalClient = iClientRepository.findBySharedKey(sharedKey);
        ClientEntity client = optionalClient.orElseThrow(() -> new ClientException("400", "No existe cliente con el Shared KEY" + sharedKey));
        return iClientMapper.toClient(optionalClient.get());
    }

    private boolean validateEmail(String email){
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(email);

        if (mather.find() == true) {
           return true;
        } else {
            return false;
        }
    }
}
