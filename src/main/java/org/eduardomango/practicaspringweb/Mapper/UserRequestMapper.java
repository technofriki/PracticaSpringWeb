package org.eduardomango.practicaspringweb.Mapper;

import org.eduardomango.practicaspringweb.DTO.UserRequest;
import org.eduardomango.practicaspringweb.model.entities.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserRequestMapper implements Mapper<UserEntity, UserRequest> {

    private ModelMapper modelMapper;

    public UserRequestMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserRequest convertToDto (UserEntity user){
        return modelMapper.map(user, UserRequest.class);
    }

    public UserEntity convertToEntity (UserRequest dto){
        return modelMapper.map(dto, UserEntity.class);
    }
}
