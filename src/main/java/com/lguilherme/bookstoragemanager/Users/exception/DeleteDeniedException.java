package com.lguilherme.bookstoragemanager.Users.exception;

import javax.persistence.EntityExistsException;

public class DeleteDeniedException extends EntityExistsException {
    public DeleteDeniedException(){
        super("Delete command denied! This user obeject is linked to a Rental!");
    }

    public DeleteDeniedException(String name){
        super(String.format("You are not allowed to delete the account of %s",name));
    }

    public DeleteDeniedException(String name,String role){
        super(String.format("Sorry,%s a logged cannot delete itself...",name,role));
    }
}