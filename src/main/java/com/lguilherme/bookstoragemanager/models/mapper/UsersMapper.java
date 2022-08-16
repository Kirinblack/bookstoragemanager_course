package com.lguilherme.bookstoragemanager.models.mapper;

import com.lguilherme.bookstoragemanager.models.Entity.Users.entity.Users;
import com.lguilherme.bookstoragemanager.models.dto.UsersDTO;
import org.mapstruct.factory.Mappers;

public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper(UsersMapper.class);
    Users  toModel(UsersDTO userDTO);

    UsersDTO toDTO(Users users);
}
