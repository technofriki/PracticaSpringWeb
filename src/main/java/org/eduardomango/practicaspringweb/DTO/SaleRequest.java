package org.eduardomango.practicaspringweb.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaleRequest {

    @NotNull(message = "Product ID is required")
    private Long id_product;

    @NotNull(message = "Client ID is required")
    private Long id_client;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Minimun sale unit is 1")
    @Max(value = 5, message = "Maximus sale unit is 5")
    private Long quantity;
}
