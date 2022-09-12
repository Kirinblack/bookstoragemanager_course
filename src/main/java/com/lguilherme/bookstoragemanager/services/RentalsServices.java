package com.lguilherme.bookstoragemanager.services;


import com.lguilherme.bookstoragemanager.Rentals.Exception.RentalAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Rentals.Exception.RentalNotFoundException;
import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.rentals.rentals;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import com.lguilherme.bookstoragemanager.models.mapper.RentalsMapper;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RentalsServices {
   private final RentalsMapper rentalsMapper = RentalsMapper.INSTANCE;


   @Autowired
   @Lazy
   RentalsRepository rentalsRepository;

   BooksServices booksServices;

   UsersServices usersServices;

    public RentalResponseDTO create(RentalRequestDTO rentalRequestDTO){
        verifyIfExists(rentalRequestDTO.getId());

        Books foundBook = booksServices.verifyAndGetIfExists(rentalRequestDTO.getBookId());
        Users foundUser = usersServices.verifyAndGetIfExists(rentalRequestDTO.getUserId());

        rentals rentalsToCreate = rentalsMapper.toModel(rentalRequestDTO);
        rentalsToCreate.setBook(foundBook);
        rentalsToCreate.setUsers(foundUser);

        rentals rentalsCreated = rentalsRepository.save(rentalsToCreate);
        return  rentalsMapper.toDTO(rentalsCreated);
    }

    public List<RentalResponseDTO> findAll(){
        return rentalsRepository.findAll()
                .stream()
                .map(rentalsMapper::toDTO)
                .collect(Collectors.toList());
    }
    public RentalResponseDTO findById(Long id){
        return rentalsRepository.findById(id)
                .map(rentalsMapper::toDTO)
                .orElseThrow(()-> new RentalNotFoundException(id));
    }
    public void deleteById(Long id){
        rentalsRepository.deleteById(id);
    }
    public void verifyIfExists(Long id){
        Optional<rentals> duplicatedRentals = rentalsRepository.findById(id);
        if (duplicatedRentals.isPresent()){
            throw new RentalAlreadyExistsException(id);
        }
    }
    public  void deleteByBook(Long id){
        Books books = booksServices.verifyAndGetIfExists(id);
        List<rentals> rentals = rentalsRepository.findByBook(books);
        rentals.stream().forEach((rentalList) ->{
            rentalsRepository.deleteById(rentalList.getId());
        });
    }

    private rentals verifyAndGetIfExists(Long id) {
        return rentalsRepository.findById(id)
                .orElseThrow(() -> new RentalAlreadyExistsException(id));
    }

}