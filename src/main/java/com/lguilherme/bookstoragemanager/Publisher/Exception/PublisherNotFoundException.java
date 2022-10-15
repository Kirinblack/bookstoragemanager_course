package com.lguilherme.bookstoragemanager.Publisher.Exception;

import javax.persistence.EntityExistsException;

public class PublisherNotFoundException extends EntityExistsException {
    public PublisherNotFoundException(Long id){
        super(String.format("Publisher with id %s not exits", id));
    }
    public PublisherNotFoundException(String name){
        super(String.format("Publisher with name %s not exists", name));
    }
}
