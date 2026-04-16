package org.eduardomango.practicaspringweb.Mapper;

import org.eduardomango.practicaspringweb.DTO.SaleResponse;
import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class SaleResponseMapper implements Mapper<SaleEntity, SaleResponse> {

    private final ModelMapper modelMapper;

    public SaleResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public SaleResponse convertToDto (SaleEntity sale){
        return modelMapper.map(sale, SaleResponse.class);
    }

    public SaleEntity convertToEntity (SaleResponse dto){
        return modelMapper.map(dto, SaleEntity.class);
    }

}
