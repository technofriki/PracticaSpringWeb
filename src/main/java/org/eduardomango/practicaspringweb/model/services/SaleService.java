package org.eduardomango.practicaspringweb.model.services;

import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.exceptions.SaleNotFoundException;
import org.eduardomango.practicaspringweb.model.repositories.IRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SaleService {
private final IRepository<SaleEntity> saleRepository;
private final ProductService productService;
private final UserService userService;


    public SaleService(IRepository<SaleEntity> saleRepository, ProductService productService, UserService userService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.userService = userService;
    }

    public List<SaleEntity> findAll(){return saleRepository.findAll();}

    public SaleEntity findById (Long id){
        return saleRepository.findAll()
                .stream()
                .filter (sale-> sale.getId()==id)
                .findFirst()
                .orElseThrow(()-> new SaleNotFoundException("Sale not found with id:"+id));
    }

public SaleEntity registerSale (Long id_product, UserEntity id_client, Long quantity){
       ProductEntity

        SaleEntity newSale = new SaleEntity();
       newSale.setId(id_product);
       newSale.setQuantity(quantity);
       newSale.setClient(id_client);
       newSale.setSaleDate(LocalDate.now());
}
}
