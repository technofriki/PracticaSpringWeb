package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.DTO.ProductRequest;
import org.eduardomango.practicaspringweb.DTO.ProductResponse;
import org.eduardomango.practicaspringweb.model.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {


    private final ProductService productService;

    public ProductController(ProductService productService) {

        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> products = productService.findAll();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {

        ProductResponse product = productService.findById(id);
        return ResponseEntity.ok(product);

    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResponse> getProductByName(@PathVariable String name) {
        ProductResponse product = productService.findByName(name);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/more-expensive-than/{price}")
    public ResponseEntity<List<ProductResponse>> getProductsMoreExpensiveThan(@PathVariable Double price) {
        List<ProductResponse> products = productService.findMoreExpensiveThan(price);
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest product) {
        ProductResponse createdProduct = productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id, @RequestBody ProductRequest product) {
        ProductResponse updatedProduct = productService.update(id, product);
        return ResponseEntity.ok(updatedProduct);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {

        productService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


}
