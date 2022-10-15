package com.lguilherme.bookstoragemanager.Users.exception;

import javax.persistence.EntityExistsException;

public class UsernameAlreadyExistsException extends EntityExistsException {
    public UsernameAlreadyExistsException(String username){
        super(String.format("User with username %s already exists!",username));
    }
}
