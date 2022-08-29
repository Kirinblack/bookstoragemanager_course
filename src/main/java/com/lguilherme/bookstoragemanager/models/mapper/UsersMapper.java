package com.lguilherme.bookstoragemanager.models.mapper;

import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserDTO;
import org.mapstruct.factory.Mappers;

public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);
    Users toModel(UserDTO userDTO);

    UserDTO toDTO(Users users);
}
