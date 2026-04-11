package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.model.entities.SaleEntity;
import org.eduardomango.practicaspringweb.model.exceptions.SaleNotFoundException;
import org.eduardomango.practicaspringweb.model.services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class  SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleEntity>> getAllSales(){
        List<SaleEntity> sales = saleService.findAll();
    return ResponseEntity.ok(sales);}

    @GetMapping ("/{id}")
    public ResponseEntity<SaleEntity> getSaleById (@PathVariable Long id){

        SaleEntity sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @PostMapping
    public ResponseEntity<SaleEntity> registerSale (@PathVariable Long id_product, @RequestParam Long id_client, @RequestParam Long quantity){
        SaleEntity sale = saleService.registerSale(id_product, id_client, quantity);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleEntity> updateSale(@PathVariable Long id, @RequestBody SaleEntity sale){

        saleService.update(id, sale);
        return ResponseEntity.ok(sale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale (@PathVariable Long id){

        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
