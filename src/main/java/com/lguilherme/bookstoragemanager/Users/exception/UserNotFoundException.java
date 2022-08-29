package com.lguilherme.bookstoragemanager.Users.exception;

import javax.persistence.EntityExistsException;

public class UserNotFoundException extends EntityExistsException {
    public UserNotFoundException(Long id){
        super(String.format("User with id%s not exists",id));
    }

    public UserNotFoundException(String username){
        super(String.format("User with username %s not exists",username));
    }
}
