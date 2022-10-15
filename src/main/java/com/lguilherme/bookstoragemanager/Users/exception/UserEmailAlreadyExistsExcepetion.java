package com.lguilherme.bookstoragemanager.Users.exception;

import javax.persistence.EntityExistsException;

public class UserEmailAlreadyExistsExcepetion extends EntityExistsException {
    public UserEmailAlreadyExistsExcepetion(String Email){
        super(String.format("User with email %S already exists!",Email));
    }
}
