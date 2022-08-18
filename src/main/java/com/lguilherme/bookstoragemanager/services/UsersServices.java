package com.lguilherme.bookstoragemanager.services;

import com.lguilherme.bookstoragemanager.models.Entity.Users.entity.Users;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UsersDTO;
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
    public UsersService(UsersRepositories userRepository,RentalsRepositories rentalRepository) {
        this.userRepository = userRepository;
        this.rentalRepository = rentalRepository;
    }

    public UsersDTO create(UsersDTO userToCreateDTO) {
        verifyIfExists(userToCreateDTO.getEmail());
        Users userToCreate = userMapper.toModel(userToCreateDTO);

        Users createdUser = userRepository.save(userToCreate);
        return userMapper.toDTO(createdUser);
    }

    public UsersDTO update(Long id, UsersDTO userToUpdateDTO) {
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

    public UsersDTO findById(Long id) {
        Users foundUser = verifyAndGetIfExists(id);
        return userMapper.toDTO(foundUser);
    }

    public List<UsersDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Users verifyAndGetIfExists(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    private void verifyIfExists(String email) {
        Optional<Users> foundUser = userRepository.findByEmail(email);
        if(foundUser.isPresent()) throw new UserAlreadyExistsException(email);
    }
}
