package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.exceptions.ProductNotFoundException;
import org.eduardomango.practicaspringweb.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
        List<ProductEntity> products = productService.findAll();
        return ResponseEntity.ok(products);}

@GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById (@PathVariable Long id){

            ProductEntity product = productService.findById(id);
            return ResponseEntity.ok(product);

}

@PostMapping
public ResponseEntity<ProductEntity> createProduct (@RequestBody ProductEntity product){
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
}

@PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct (@PathVariable Long id, @RequestBody ProductEntity product){

            product.setId(id);
            productService.save(product);
            return ResponseEntity.ok(product);

}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id){

            ProductEntity product = productService.findById(id);
            productService.delete(product);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }



}
