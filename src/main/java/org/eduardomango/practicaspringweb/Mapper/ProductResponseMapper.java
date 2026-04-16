package org.eduardomango.practicaspringweb.Mapper;

import org.eduardomango.practicaspringweb.DTO.ProductResponse;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductResponseMapper implements Mapper <ProductEntity, ProductResponse> {

    private final ModelMapper modelMapper;

    public ProductResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductResponse convertToDto (ProductEntity product){
        return modelMapper.map(product, ProductResponse.class);
    }

    public ProductEntity convertToEntity (ProductResponse dto){
        return modelMapper.map(dto, ProductEntity.class);
    }
}
