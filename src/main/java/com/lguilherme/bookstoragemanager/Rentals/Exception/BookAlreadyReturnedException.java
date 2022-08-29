package com.lguilherme.bookstoragemanager.Rentals.Exception;

import javax.persistence.EntityExistsException;

public class BookAlreadyReturnedException extends EntityExistsException {
    public BookAlreadyReturnedException(String user, String book){
        super(String.format("The user %s has already returned the book %s", user,book));
    }
}
