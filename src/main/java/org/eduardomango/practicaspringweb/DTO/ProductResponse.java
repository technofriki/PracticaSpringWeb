package org.eduardomango.practicaspringweb.DTO;

import lombok.Data;

@Data
public class ProductResponse {

    private Long id;
    private String name;
    private Double price;
    private String description;
}
