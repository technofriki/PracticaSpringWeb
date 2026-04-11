package org.eduardomango.practicaspringweb.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ProductRequest {
    @NotBlank(message = "Product Name is required")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive and not zero")
    private Double price;

    @NotBlank (message = "Product Description is required")
    @Size(max = 100, message = "Desciption must not exceed 100 characters")
    private String description;
}
