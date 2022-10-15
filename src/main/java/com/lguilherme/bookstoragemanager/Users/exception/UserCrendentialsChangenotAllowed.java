package com.lguilherme.bookstoragemanager.Users.exception;

import javax.persistence.EntityExistsException;

public class UserCrendentialsChangenotAllowed extends EntityExistsException {
    private UserCrendentialsChangenotAllowed(){
        super("Incorrect password");
    }
}
