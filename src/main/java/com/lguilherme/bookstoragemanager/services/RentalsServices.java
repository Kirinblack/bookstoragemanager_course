package com.lguilherme.bookstoragemanager.services;


import com.lguilherme.bookstoragemanager.Rentals.Exception.RentalNotFoundException;
import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.rentals.rentals;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestUpdateDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.AuthenticatedUser;
import com.lguilherme.bookstoragemanager.models.mapper.RentalsMapper;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;

@Service
public class RentalsServices {

    private final RentalsMapper rentalsMapper = RentalsMapper.INSTANCE;
    private final Object BooksServices;

    private RentalsRepositories rentalsRepositories;

    private UsersServices usersServices;

    @Autowired
    public  RentalsServices(RentalsRepositories rentalsRepositories, BooksServices booksServices, UsersServices usersServices){
        this.rentalsRepositories = rentalsRepositories;
        this.BooksServices = booksServices;
        this.usersServices = usersServices;
    }
    public RentalResponseDTO create(RentalRequestDTO rentalRequestDTO) {
        Books foundBook = BooksServices.verifyAndGetIfExists(rentalRequestDTO.getBookId());
        Users foundUser = UsersServices.verifyAndGetIfExists(rentalRequestDTO.getUserId());
        String rentStatus = "Não devolvido";

        rentals rentToSave = RentalsMapper.toModel(RentalRequestDTO);
        rentToSave.setBook(foundBook);
        rentToSave.setUsers(foundUser);
        rentToSave.setReturnDate(rentStatus);
        verifyIfExists(rentToSave.getBook(), rentToSave.getUsers());

        Books alterBook = rentToSave.getBook();

        validateDate(rentalRequestDTO, rentToSave, alterBook);
        alterBook.setQuantity(alterBook.getQuantity() - 1);
        alterBook.setRentedQuantity(alterBook.getRentedQuantity() + 1);

        rentals savedRent = RentalsRepositories.save(rentToSave);
        return rentalsMapper.toDTO(savedRent);
    }

    private void verifyIfExists(Books book, Users users) {

    }

    private void validateDate(RentalRequestDTO rentalRequestDTO, rentals rentToSave, Books alterBook) {

    }

    public RentalResponseDTO findById(Long id, AuthenticatedUser authenticatedUser) {
        Users foundAuthenticatedUser = UsersServices.verifyAndGetUserIfExists(authenticatedUser.getUsername());
        if(isAdmin(foundAuthenticatedUser)) {
            return rentalsRepositories.findById(id)
                    .map(RentalsMapper::toDTO)
                    .orElseThrow(() -> new RentalNotFoundException(id));
        }

    private void validateDate(RentalRequestDTO RentalRequestDTO, rentals rentToSave, Books alterBook) {
    }

    private void verifyIfExists(Books Object book;
        book, Users Object users;
        users) {
    }
}

    private boolean isAdmin(Users foundAuthenticatedUser) {
        return false;
    }

    public RentalResponseDTO update(Long id, AuthenticatedUser authenticatedUser, RentalRequestUpdateDTO rentalRequestUpdateDTO) {
        return null;
    }

    public void delete(Long id, AuthenticatedUser authenticatedUser) {
    }

    public Page<RentalResponseDTO> findAll(AuthenticatedUser authenticatedUser, Pageable pageable) {
    }
