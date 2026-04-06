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

public SaleEntity registerSale (Long id_product, Long id_client, Long quantity){
       ProductEntity product = productService.findById(id_product);
       UserEntity user = userService.findById(id_client);

       SaleEntity sale = new SaleEntity();
       sale.setId(System.currentTimeMillis());
       sale.setProducts(product);
       sale.setQuantity(quantity);
       sale.setClient(user);
       sale.setSaleDate(LocalDate.now());
       saleRepository.save(sale);
       return sale;
}
public void delete (Long id){
        SaleEntity sale = findById(id);
        saleRepository.delete(sale);
    }

    public void update (Long id, SaleEntity sale){
        findById(id);
        sale.setId(id);
        saleRepository.update(sale);
    }


}
