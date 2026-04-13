package org.eduardomango.practicaspringweb.DTO;

import lombok.Data;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;

@Data
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String password;

    public UserResponse(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
