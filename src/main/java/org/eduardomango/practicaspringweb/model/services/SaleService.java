package org.eduardomango.practicaspringweb.model.services;

import org.eduardomango.practicaspringweb.DTO.SaleRequest;
import org.eduardomango.practicaspringweb.DTO.SaleResponse;
import org.eduardomango.practicaspringweb.Mapper.SaleRequestMapper;
import org.eduardomango.practicaspringweb.Mapper.SaleResponseMapper;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.exceptions.SaleNotFoundException;
import org.eduardomango.practicaspringweb.model.repositories.IRepository;
import org.eduardomango.practicaspringweb.model.repositories.SaleRepository;
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

    public SaleService(IRepository<SaleEntity> saleRepository, ProductService productService, UserService userService, SaleResponseMapper saleResponseMapper, SaleRequestMapper saleRequestMapper) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.userService = userService;
        this.saleResponseMapper = saleResponseMapper;
        this.saleRequestMapper = saleRequestMapper;
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

    public SaleEntity registerSale(SaleRequest saleRequest) {
        ProductEntity product = productService.findById(saleRequest.getId_product());
        UserEntity user = userService.findById(saleRequest.getId_client());

        SaleEntity sale = new SaleEntity();
        sale.setId(System.currentTimeMillis());
        sale.setProducts(product);
        sale.setQuantity(saleRequest.getQuantity());
        sale.setClient(user);
        sale.setSaleDate(LocalDate.now());
        saleRepository.save(sale);
        return new SaleResponse(sale);
    }

    public void delete(Long id) {
        SaleEntity sale = saleRepository.findAll()
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new SaleNotFoundException("Sale not found with id:" + id));

        saleRepository.delete(sale);
    }

    public void update(Long id, SaleRequest saleRequest) {
        SaleEntity existingSale = saleRepository.findAll()
                .stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElseThrow(() -> new SaleNotFoundException("Sale not found with id:" + id));
        existingSale.setProducts(productService.findById(saleRequest.getId_product()));
        existingSale.setClient(userService.findById(saleRequest.getId_client()));
        existingSale.setQuantity(saleRequest.getQuantity());
        saleRepository.update(existingSale);
        /// NO ENTENDI NADA EN LOS SETEOS
    }


}
