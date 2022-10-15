package com.lguilherme.bookstoragemanager.models.mapper;

import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);

    Users toModel(UserRequestDTO userRequestDTO);

    Users toModel(UserResponseDTO userResponseDTO);

    UserResponseDTO toDTO(Users users);
}
