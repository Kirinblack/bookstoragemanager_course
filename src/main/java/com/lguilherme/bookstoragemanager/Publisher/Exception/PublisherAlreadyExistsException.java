package com.lguilherme.bookstoragemanager.Publisher.Exception;

import javax.persistence.EntityExistsException;

public class PublisherAlreadyExistsException extends EntityExistsException {
    public PublisherAlreadyExistsException(String name){
        super(String.format("Publisher with name %s already exits", name));
    }
}
