package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductEntity> getAllProducts(){return productService.findAll();}

@GetMapping("/{id}")
    public ProductEntity getProductById (@PathVariable Long id){
        return productService.findById(id);
}

@PostMapping
public void createProduct (@RequestBody ProductEntity product){
        productService.save(product);
}

@PutMapping("/{id}")
    public void updateProduct (@PathVariable Long id, @RequestBody ProductEntity product){
        product.setId(id);
        productService.update(product);
}

@DeleteMapping("/{id}")
    public void deleteProduct (@PathVariable Long id){
        ProductEntity product = productService.findById(id);
        productService.delete(product);
}

}
