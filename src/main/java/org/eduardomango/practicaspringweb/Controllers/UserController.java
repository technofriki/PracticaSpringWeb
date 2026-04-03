package org.eduardomango.practicaspringweb.Controllers;

import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id){
        return userService.findById(id);
    }

    @PostMapping
    public void createUser (@RequestBody UserEntity user){
        userService.save(user);
    }

    @PutMapping("/{id}")
    public void updateUser (@PathVariable Long id, @RequestBody UserEntity user){
        user.setId(id);
        userService.update(user);

    }

@DeleteMapping("/{id}")
    public void deleteUser (@PathVariable Long id){
        UserEntity user = userService.findById(id);
        userService.delete(user);
}
}
