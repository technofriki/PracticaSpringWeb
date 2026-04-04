package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    private SaleService saleService;

    @GetMapping
    public ResponseEntity<List<SaleEntity>> getAllSales(){
        List<SaleEntity> sales = saleService.findAll();
    return ResponseEntity.ok(sales);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<SaleEntity> getSaleById (@PathVariable Long id){
        try {
            SaleEntity sale = saleService.findById(id);
            return ResponseEntity.ok(sale);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<SaleEntity> registerSale (@RequestParam Long id_product, @RequestParam Long id_client, @RequestParam Long quantity){
        saleService.registerSale(id_product, id_client, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleEntity> updateSale(@PathVariable Long id, @RequestBody SaleEntity sale){
        try{

            saleService.update(sale, id);
            return ResponseEntity.ok(sale);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale (@PathVariable Long id){
        try{
            saleService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
