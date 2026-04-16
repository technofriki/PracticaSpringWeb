package org.eduardomango.practicaspringweb.model.services;


import org.eduardomango.practicaspringweb.DTO.UserRequest;
import org.eduardomango.practicaspringweb.DTO.UserResponse;
import org.eduardomango.practicaspringweb.Mapper.ProductResponseMapper;
import org.eduardomango.practicaspringweb.Mapper.UserRequestMapper;
import org.eduardomango.practicaspringweb.Mapper.UserResponseMapper;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.eduardomango.practicaspringweb.model.exceptions.UserNotFoundException;
import org.eduardomango.practicaspringweb.model.repositories.IRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final IRepository<UserEntity> userRepository;
    private final UserResponseMapper userResponseMapper;
    private final UserRequestMapper userRequestMapper;

    public UserService(IRepository<UserEntity> userRepository, UserResponseMapper userResponseMapper, UserRequestMapper userRequestMapper) {
        this.userRepository = userRepository;
        this.userResponseMapper = userResponseMapper;
        this.userRequestMapper = userRequestMapper;
    }

    public List<UserResponse> findAll() {

        return userRepository.findAll()
                .stream()
                .map(userResponseMapper::convertToDto)
                .collect(Collectors.toList());
    }

    public UserResponse findById(long id) {
        UserEntity user = userRepository.findAll()
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
        return userResponseMapper.convertToDto(user);
    }

    public UserResponse findByUsername(String username) {
        UserEntity user = userRepository.findAll()
                .stream()
                .filter(u -> u.getUsername().equalsIgnoreCase(username))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
        return userResponseMapper.convertToDto(user);
    }

    public UserResponse findByEmail(String email) {
        UserEntity user = userRepository.findAll()
                .stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email))
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
        return userResponseMapper.convertToDto(user);
    }

    public void save(UserRequest u) {
        UserEntity user = userRequestMapper.convertToEntity(u);
        userRepository.save(user);
    }

    public void delete(Long id) {
        UserEntity user = userRepository.findAll()
                .stream()
                .filter(u -> u.getId() == id)
                .findFirst()
                .orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }

    public void update(Long id, UserRequest u) {
        findById(id);
        UserEntity user = userRequestMapper.convertToEntity(u);
        user.setId(id);
        userRepository.update(user);
    }
}
