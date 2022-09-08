package com.lguilherme.bookstoragemanager.services;


import com.lguilherme.bookstoragemanager.Rentals.Exception.RentalAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Rentals.Exception.RentalNotFoundException;
import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.rentals.rentals;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import com.lguilherme.bookstoragemanager.models.mapper.RentalsMapper;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepositories;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalsServices {
   private final RentalsMapper rentalsMapper = RentalsMapper.INSTANCE;

   private final RentalsRepositories rentalsRepositories;

   private final BooksServices booksServices;

   private final UsersServices usersServices;


    public RentalsServices(RentalsRepositories rentalsRepositories, BooksServices booksServices, UsersServices usersServices) {
        this.rentalsRepositories = rentalsRepositories;
        this.booksServices = booksServices;
        this.usersServices = usersServices;
    }

    public RentalResponseDTO create(RentalRequestDTO rentalRequestDTO){
        verifyIfExists(rentalRequestDTO.getId());

        Books foundBook = booksServices.verifyAndGetIfExists(rentalRequestDTO.getBookId());
        Users foundUser = usersServices.verifyAndGetIfExists(rentalRequestDTO.getUserId());

        rentals rentalsToCreate = RentalsMapper.toModel(rentalRequestDTO);
        rentalsToCreate.setBook(foundBook);
        rentalsToCreate.setUsers(foundUser);

        rentals rentalsCreated = rentalsRepositories.save(rentalsToCreate);
        return  rentalsMapper.toDTO(rentalsCreated);
    }

    public List<RentalResponseDTO> findAll(){
        return rentalsRepositories.findAll()
                .stream()
                .map(rentalsMapper::toDTO)
                .collect(Collectors.toList());
    }
    public RentalResponseDTO findById(Long id){
        return rentalsRepositories.findById(id)
                .map(rentalsMapper::toDTO)
                .orElseThrow(()-> new RentalNotFoundException(id));
    }
    public void deleteById(Long id){
        rentalsRepositories.deleteById(id);
    }
    public void verifyIfExists(Long id){
        Optional<rentals> duplicatedRentals = rentalsRepositories.findById(id);
        if (duplicatedRentals.isPresent()){
            throw new RentalAlreadyExistsException(id);
        }
    }
    public  void deleteByBook(Long id){
        Books books = booksServices.verifyAndGetIfExists(id);
        List<rentals> rentals = rentalsRepositories.findByBook(books);
        rentals.stream().forEach((rentalList) ->{
            rentalsRepositories.deleteById(rentalList.getId());
        });
    }

    private rentals verifyAndGetIfExists(Long id) {
        return rentalsRepositories.findById(id)
                .orElseThrow(() -> new RentalAlreadyExistsException(id));
    }

}