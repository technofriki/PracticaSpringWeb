package org.eduardomango.practicaspringweb.Mapper;

import org.eduardomango.practicaspringweb.DTO.UserResponse;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserResponseMapper implements Mapper<UserEntity, UserResponse> {

    private final ModelMapper modelMapper;

    public UserResponseMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserResponse convertToDto (UserEntity user){
        return modelMapper.map(user, UserResponse.class);
    }

    public UserEntity convertToEntity (UserResponse dto){
        return modelMapper.map(dto, UserEntity.class);
    }
}
