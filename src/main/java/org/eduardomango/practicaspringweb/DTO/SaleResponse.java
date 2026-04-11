package org.eduardomango.practicaspringweb.DTO;

import lombok.Data;
import org.eduardomango.practicaspringweb.model.entities.ProductEntity;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;

import java.time.LocalDate;

@Data
public class SaleResponse {
    private Long id;
    private ProductResponse products;
    private Long quantity;
    private UserResponse client;
    private LocalDate saleDate;
}
