package com.lguilherme.bookstoragemanager.Users.exception;

import javax.persistence.EntityExistsException;

public class InvalidCredentialsChange extends EntityExistsException {
    public InvalidCredentialsChange(){
        super("You may only change one of your unique credentials at time!");
    }

    public InvalidCredentialsChange(String username){
        super(String.format("You are not allowed to change the credentials of %s",username));
    }
}
