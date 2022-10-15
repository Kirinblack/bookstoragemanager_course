package com.lguilherme.bookstoragemanager.Rentals.Exception;

import com.lguilherme.bookstoragemanager.models.Entity.books.Books;

import javax.persistence.EntityExistsException;

public class RentalAlreadyExistsException extends EntityExistsException {
    public RentalAlreadyExistsException(Long id){
        super(String.format("Rental with id %s already exists!", id));
    }
}
