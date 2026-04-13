package org.eduardomango.practicaspringweb.DTO;

import lombok.Data;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String description;

    public ProductResponse(ProductEntity product) {

        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.description = product.getDescription();
    }
}
