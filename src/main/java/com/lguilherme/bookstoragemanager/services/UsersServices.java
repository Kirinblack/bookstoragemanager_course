package com.lguilherme.bookstoragemanager.services;

import com.lguilherme.bookstoragemanager.Books.Exception.DeleteDeniedException;
import com.lguilherme.bookstoragemanager.Users.exception.UserAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Users.exception.UserNotFoundException;
import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserDTO;
import com.lguilherme.bookstoragemanager.models.mapper.UsersMapper;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepositories;
import com.lguilherme.bookstoragemanager.repositories.UsersRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersServices {

    private final static UsersMapper userMapper = UsersMapper.INSTANCE;

    private UsersRepositories userRepository;

    private RentalsRepositories rentalRepository;

    @Autowired
    public void UsersService(UsersRepositories userRepository, RentalsRepositories rentalRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public UserDTO create(UserDTO userToCreateDTO) {
        verifyIfExists(userToCreateDTO.getEmail());
        Users userToCreate = userMapper.toModel(userToCreateDTO);

        Users createdUser = userRepository.save(userToCreate);
        return userMapper.toDTO(createdUser);
    }

    public UserDTO update(Long id, UserDTO userToUpdateDTO) {
        Users foundUser = verifyAndGetIfExists(id);

        userToUpdateDTO.setId(foundUser.getId());
        Users userToUpdate = userMapper.toModel(userToUpdateDTO);

        Users updatedUser = userRepository.save(userToUpdate);
        return userMapper.toDTO(updatedUser);
    }

    public void delete(Long id) {
        Users userToDelete = verifyAndGetIfExists(id);
        if(rentalRepository.findByUsers(userToDelete).isPresent()) throw new DeleteDeniedException();
        userRepository.deleteById(id);
    }

    public UserDTO findById(Long id) {
        Users foundUser = verifyAndGetIfExists(id);
        return userMapper.toDTO(foundUser);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Users verifyAndGetIfExists(Long id) {
        return (Users) userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private void verifyIfExists(String email) {
        Optional<Users> foundUser = userRepository.findByEmail(email);
        if(foundUser.isPresent()) throw new UserAlreadyExistsException(email);
    }

    public void deleteById(Long id) {
    }
}
