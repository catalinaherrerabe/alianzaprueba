package com.example.mapper;
import com.example.DTO.ClientDTO;
import com.example.entities.ClientEntity;
import org.mapstruct.*;

import java.util.List;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface IClientMapper {

    ClientDTO toClient(ClientEntity clientEntity);

    @InheritInverseConfiguration
    ClientEntity fromClient(ClientDTO clientDTO);

    List<ClientDTO> toClientList(List<ClientEntity> entities);

    List<ClientEntity> fromClientList(List<ClientDTO> dtos);
}
