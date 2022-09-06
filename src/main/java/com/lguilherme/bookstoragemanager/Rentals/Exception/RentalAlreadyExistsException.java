package com.lguilherme.bookstoragemanager.Rentals.Exception;

import javax.persistence.EntityExistsException;

public class RentalAlreadyExistsException extends EntityExistsException {
    public RentalAlreadyExistsException(Object book){
        super(String.format("The user %s has already rented the book %s",user,book));
    }
}
