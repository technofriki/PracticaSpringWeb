package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAllProducts(){
        List<ProductEntity> products = productService.findAll();
        return ResponseEntity.ok(products);}

@GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getProductById (@PathVariable Long id){
        try{
            ProductEntity product = productService.findById(id);
            return ResponseEntity.ok(product);
        } catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
}

@PostMapping
public ResponseEntity<ProductEntity> createProduct (@RequestBody ProductEntity product){
        productService.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
}

@PutMapping("/{id}")
    public ResponseEntity<ProductEntity> updateProduct (@PathVariable Long id, @RequestBody ProductEntity product){
        try{
            product.setId(id);
            productService.save(product);
            return ResponseEntity.ok(product);
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
}

@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct (@PathVariable Long id){
        try{
            ProductEntity product = productService.findById(id);
            productService.delete(product);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

}

}
