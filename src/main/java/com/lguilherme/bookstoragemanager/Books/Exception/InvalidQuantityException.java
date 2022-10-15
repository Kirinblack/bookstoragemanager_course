package com.lguilherme.bookstoragemanager.Books.Exception;

import javax.persistence.EntityExistsException;

public class InvalidQuantityException extends EntityExistsException {
    public InvalidQuantityException(){
        super("Invalid book quantity");
    }
}
