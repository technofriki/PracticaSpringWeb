package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public List<SaleEntity> getAllSales(){return saleService.findAll();}

    @GetMapping ("/{id}")
    public SaleEntity getSaleById (@PathVariable Long id){
        return saleService.findById(id);
    }

    @PostMapping
    public SaleEntity registerSale (@RequestParam Long id_product, @RequestParam Long id_client, @RequestParam Long quantity){
        return saleService.registerSale(id_product, id_client, quantity);
    }

    @PutMapping("/{id}")
    public void updateSale(@PathVariable Long id, @RequestBody SaleEntity sale){
        saleService.update(sale, id);
    }

    @DeleteMapping("/{id}")
    public void deleteSale (@PathVariable Long id){
        saleService.delete(id);
    }
}
