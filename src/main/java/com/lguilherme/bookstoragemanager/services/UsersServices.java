package com.lguilherme.bookstoragemanager.services;

import com.lguilherme.bookstoragemanager.Books.Exception.DeleteDeniedException;
import com.lguilherme.bookstoragemanager.Users.exception.UserAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Users.exception.UserNotFoundException;
import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserResponseDTO;
import com.lguilherme.bookstoragemanager.models.mapper.UsersMapper;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepository;
import com.lguilherme.bookstoragemanager.repositories.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServices {

    private final static UsersMapper userMapper = UsersMapper.INSTANCE;

    @Autowired
    @Lazy
    UsersRepository usersRepository;
    RentalsRepository rentalsRepository;

    public UserResponseDTO create(UserRequestDTO userToCreateDTO) {
        verifyIfExists(userToCreateDTO.getEmail());
        Users userToCreate = userMapper.toModel(userToCreateDTO);

        Users createdUser = usersRepository.save(userToCreate);
        return userMapper.toDTO(createdUser);
    }

    public UserResponseDTO update(Long id, UserRequestDTO userToUpdateDTO) {
        Users foundUser = verifyAndGetIfExists(id);

        userToUpdateDTO.setId(foundUser.getId());
        Users userToUpdate = userMapper.toModel(userToUpdateDTO);

        Users updatedUser = usersRepository.save(userToUpdate);
        return userMapper.toDTO(updatedUser);
    }

    public void delete(Long id) {
        Users userToDelete = verifyAndGetIfExists(id);
        if(rentalsRepository.findByUsers(userToDelete).isPresent()) throw new DeleteDeniedException();
        usersRepository.deleteById(id);
    }

    public UserResponseDTO findById(Long id) {
        Users foundUser = verifyAndGetIfExists(id);
        return userMapper.toDTO(foundUser);
    }

    public List<UserResponseDTO> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Users verifyAndGetIfExists(Long id) {
        return (Users) usersRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private void verifyIfExists(String email) {
        Optional<Users> foundUser = usersRepository.findByEmail(email);
        if(foundUser.isPresent()) throw new UserAlreadyExistsException(email);
    }

    public void deleteById(Long id) {
    }
}
