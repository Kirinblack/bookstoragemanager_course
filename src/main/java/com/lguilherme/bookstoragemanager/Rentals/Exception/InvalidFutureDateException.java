package com.lguilherme.bookstoragemanager.Rentals.Exception;

import javax.persistence.EntityExistsException;

public class InvalidFutureDateException extends EntityExistsException {
    public InvalidFutureDateException(){
        super("You may not rent a book in a future date");
    }
}
