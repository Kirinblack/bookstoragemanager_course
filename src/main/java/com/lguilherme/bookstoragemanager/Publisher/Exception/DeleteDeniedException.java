package com.lguilherme.bookstoragemanager.Publisher.Exception;

import javax.persistence.EntityExistsException;

public class DeleteDeniedException extends EntityExistsException {
    public DeleteDeniedException(){
        super("Delete command denied! This publisher object is linked to a book!");
    }
}