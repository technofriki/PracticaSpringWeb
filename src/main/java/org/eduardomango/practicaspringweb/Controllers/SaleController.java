package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.DTO.SaleRequest;
import org.eduardomango.practicaspringweb.DTO.SaleResponse;
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
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleResponse>> getAllSales() {
        List<SaleResponse> sales = saleService.findAll();
        return ResponseEntity.ok(sales);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SaleResponse> getSaleById(@PathVariable Long id) {

        SaleResponse sale = saleService.findById(id);
        return ResponseEntity.ok(sale);
    }

    @PostMapping("/register/{id_product}")
    public ResponseEntity<SaleResponse> registerSale(@PathVariable Long id_product, @RequestParam Long id_client, @RequestParam Long quantity) {
        SaleRequest saleRequest = new SaleRequest();
        saleRequest.setId_product(id_product);
        saleRequest.setId_client(id_client);
        saleRequest.setQuantity(quantity);
        SaleResponse sale = saleService.registerSale(saleRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(sale);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SaleResponse> updateSale(@PathVariable Long id, @RequestBody SaleRequest sale) {

        SaleResponse updatedSale = saleService.update(id, sale);
        return ResponseEntity.ok(updatedSale);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSale(@PathVariable Long id) {

        saleService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
