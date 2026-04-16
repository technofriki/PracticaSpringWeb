package org.eduardomango.practicaspringweb.Mapper;

import org.eduardomango.practicaspringweb.DTO.ProductRequest;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductRequestMapper implements Mapper<ProductEntity, ProductRequest> {

    private final ModelMapper modelMapper;

    public ProductRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public ProductRequest convertToDto(ProductEntity product) {
        return modelMapper.map(product, ProductRequest.class);
    }

    public ProductEntity convertToEntity (ProductRequest dto){
        return modelMapper.map(dto, ProductEntity.class);
    }
}
