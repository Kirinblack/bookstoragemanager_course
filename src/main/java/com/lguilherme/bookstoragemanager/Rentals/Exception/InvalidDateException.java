package com.lguilherme.bookstoragemanager.Rentals.Exception;

import javax.persistence.EntityExistsException;

public class InvalidDateException  extends EntityExistsException {
    public InvalidDateException(){
        super("Return forecast may not be before the rental date");
    }
}
