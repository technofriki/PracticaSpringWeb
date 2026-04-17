package org.eduardomango.practicaspringweb.model.services;

import org.eduardomango.practicaspringweb.DTO.ProductRequest;
import org.eduardomango.practicaspringweb.DTO.ProductResponse;
import org.eduardomango.practicaspringweb.Mapper.ProductRequestMapper;
import org.eduardomango.practicaspringweb.Mapper.ProductResponseMapper;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.exceptions.ProductNotFoundException;
import org.eduardomango.practicaspringweb.model.exceptions.UserNotFoundException;
import org.eduardomango.practicaspringweb.model.repositories.IRepository;
import org.eduardomango.practicaspringweb.model.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final IRepository<ProductEntity> productRepository;
    private final ProductResponseMapper productResponseMapper;
    private final ProductRequestMapper productRequestMapper;

    public ProductService(IRepository<ProductEntity> productRepository, ProductResponseMapper productResponseMapper, ProductRequestMapper productRequestMapper) {
        this.productRepository = productRepository;
        this.productResponseMapper = productResponseMapper;
        this.productRequestMapper = productRequestMapper;
    }

    public List<ProductResponse> findAll() {

        return productRepository.findAll().stream()
                .map(productResponseMapper::convertToDto)
                .collect(Collectors.toList());
    }
    public ProductResponse findById(long id) {
        ProductEntity product = productRepository.findAll()
                .stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
        return productResponseMapper.convertToDto(product);
    }

    public ProductResponse findByName(String name){
        ProductEntity product = productRepository.findAll()
                .stream()
                .filter(user -> user.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
        return productResponseMapper.convertToDto(product);
    }

    public List<ProductResponse> findMoreExpensiveThan(Double price){
        return productRepository.findAll()
                .stream()
                .filter(p -> p.getPrice() > price)
                .map(productResponseMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public ProductResponse save(ProductRequest p) {

        ProductEntity product = productRequestMapper.convertToEntity(p);
        productRepository.save(product);
        return productResponseMapper.convertToDto(product);
    }

    public void delete(Long id) {
        ProductEntity product = productRepository.findAll()
                .stream()
                .filter(p-> p.getId()==id)
                .findFirst()
                .orElseThrow(ProductNotFoundException::new);
        productRepository.delete(product);
    }

    public ProductResponse update(Long id, ProductRequest p) {
        findById(id);
        ProductEntity product = productRequestMapper.convertToEntity(p);
        product.setId(id);
        productRepository.update(product);
        return productResponseMapper.convertToDto(product);
    }
}
