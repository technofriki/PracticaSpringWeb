package org.eduardomango.practicaspringweb.DTO;

import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
public class ErrorDetails {
private LocalDateTime timestamp;
private String message;
private String details;

    public ErrorDetails(String message, String details) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.details = details;
    }
}
