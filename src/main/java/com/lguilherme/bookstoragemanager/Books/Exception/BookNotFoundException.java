package com.lguilherme.bookstoragemanager.Books.Exception;

import javax.persistence.EntityExistsException;

public class BookNotFoundException extends EntityExistsException {
    public  BookNotFoundException(Long id){
        super(String.format("Book with id %s not exists", id));
    }

}
