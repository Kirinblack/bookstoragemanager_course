package com.lguilherme.bookstoragemanager.Rentals.Exception;

import javax.persistence.EntityExistsException;

public class RentalNotFoundException extends EntityExistsException {
    public RentalNotFoundException(Long id){
        super(String.format("Rent with id %s not exists!", id));
    }
}
