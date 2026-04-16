package org.eduardomango.practicaspringweb.Mapper;

import org.eduardomango.practicaspringweb.DTO.SaleRequest;
import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SaleRequestMapper implements Mapper<SaleEntity, SaleRequest> {

    private final ModelMapper modelMapper;

    public SaleRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SaleRequest convertToDto (SaleEntity sale){
        return modelMapper.map(sale, SaleRequest.class);
    }

    public SaleEntity convertToEntity (SaleRequest dto){
        return modelMapper.map(dto, SaleEntity.class);
    }
}
