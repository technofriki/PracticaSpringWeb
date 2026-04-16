package org.eduardomango.practicaspringweb.model.services;

import org.eduardomango.practicaspringweb.DTO.ProductResponse;
import org.eduardomango.practicaspringweb.DTO.SaleRequest;
import org.eduardomango.practicaspringweb.DTO.SaleResponse;
import org.eduardomango.practicaspringweb.DTO.UserResponse;
import org.eduardomango.practicaspringweb.Mapper.ProductResponseMapper;
import org.eduardomango.practicaspringweb.Mapper.SaleRequestMapper;
import org.eduardomango.practicaspringweb.Mapper.SaleResponseMapper;
import org.eduardomango.practicaspringweb.Mapper.UserResponseMapper;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.exceptions.SaleNotFoundException;
import org.eduardomango.practicaspringweb.model.repositories.IRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleService {
    private final IRepository<SaleEntity> saleRepository;
    private final ProductService productService;
    private final UserService userService;
    private final SaleResponseMapper saleResponseMapper;
    private final SaleRequestMapper saleRequestMapper;
    private final ProductResponseMapper productResponseMapper;
    private final UserResponseMapper userResponseMapper;

    public SaleService(IRepository<SaleEntity> saleRepository, ProductService productService, UserService userService, SaleResponseMapper saleResponseMapper, SaleRequestMapper saleRequestMapper, ProductResponseMapper productResponseMapper, UserResponseMapper userResponseMapper) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.userService = userService;
        this.saleResponseMapper = saleResponseMapper;
        this.saleRequestMapper = saleRequestMapper;
        this.productResponseMapper = productResponseMapper;
        this.userResponseMapper = userResponseMapper;
    }

    public List<SaleResponse> findAll() {
        return saleRepository.findAll().stream()
                .map(saleResponseMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public SaleResponse findById(Long id) {
        SaleEntity sale = saleRepository.findAll()
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(SaleNotFoundException::new);
        return saleResponseMapper.convertToDto(sale);
    }

    public SaleResponse registerSale(SaleRequest saleRequest) {

        ProductResponse productResponse = productService.findById(saleRequest.getId_product());
        ProductEntity product = productResponseMapper.convertToEntity(productResponse);
        UserResponse userResponse = userService.findById(saleRequest.getId_client());
        UserEntity user = userResponseMapper.convertToEntity(userResponse);

        SaleEntity sale = new  SaleEntity();
        sale.setId(System.currentTimeMillis());
        sale.setProducts(product);
        sale.setQuantity(saleRequest.getQuantity());
        sale.setClient(user);
        sale.setSaleDate(LocalDate.now());

        saleRepository.save(sale);
        return saleResponseMapper.convertToDto(sale);
    }

    public void delete(Long id) {
        SaleEntity sale = saleRepository.findAll()
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(SaleNotFoundException::new);

        saleRepository.delete(sale);
    }

    public void update(Long id, SaleRequest s) {
        findById(id);
        SaleEntity sale = saleRequestMapper.convertToEntity(s);
        sale.setId(id);
        saleRepository.save(sale);
    }


}
