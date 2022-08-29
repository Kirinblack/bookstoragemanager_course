package com.lguilherme.bookstoragemanager.Books.Exception;

import javax.persistence.EntityExistsException;

public class BookAlreadyExistsException extends EntityExistsException {
    public BookAlreadyExistsException(String name){
        super(String.format("Book with name %s already exists", name));
    }
}
