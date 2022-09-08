package com.lguilherme.bookstoragemanager.services;


import com.lguilherme.bookstoragemanager.Exception.UpdateHasNoChangeException;
import com.lguilherme.bookstoragemanager.Publisher.Exception.PublisherAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Publisher.Exception.PublisherNotFoundException;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import com.lguilherme.bookstoragemanager.models.mapper.PublisherMapper;
import com.lguilherme.bookstoragemanager.repositories.PublisherRepositories;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PublisherServices {
    private static  final PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    private final PublisherRepositories publisherRepositories;

    private final BooksServices booksServices;


    public PublisherServices(PublisherRepositories publisherRepositories, BooksServices booksServices) {
        this.publisherRepositories = publisherRepositories;
        this.booksServices = booksServices;
    }

    public Page<PublisherResponseDTO> findAll(Pageable pageable){
        return publisherRepositories.findAll(pageable)
                .map(publisherRepositories::toDTO);
    }

    public PublisherResponseDTO findById(Long id){
        return  publisherRepositories.findById(id)
                .map(publisherMapper::toDTO)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    public MessageDTO create(PublisherRequestDTO publisherRequestDTO){
        verifyIfExists(publisherRequestDTO.getName(), publisherRequestDTO.getCode());

        Publisher publisherToCreate = publisherMapper.toModel(publisherRequestDTO);
        publisherToCreate.setRegistrationDate(LocalDate.now());
        Publisher createdPublisher = publisherRepositories.save(publisherToCreate);

        String createdMessage = String.format("Publishr %s with id %s successfully created!!",createdPublisher.getName(),createdPublisher.getId());
        return  MessageDTO.builder()
                .message(createdMessage)
                .build();
    }

    public  MessageDTO update(Long id, PublisherRequestDTO publisherRequestDTO){
        Publisher foundPublisher = verifyAndGetIfExists(id);
        publisherRequestDTO.setId(foundPublisher.getId());
        Publisher publisherToCreate = publisherMapper.toModel(publisherRequestDTO);
        publisherToCreate.setRegistrationDate(foundPublisher.getRegistrationDate());
        checkForChangesToUpdate(foundPublisher, publisherToCreate);
        Publisher createdPublisher = publisherRepositories.save(publisherToCreate);

        String createdMessage = String.format("Publisher with id %d has been updated successfully!!",createdPublisher.getId());
    }

    private void verifyIfExists(String name,String code) {
        Optional<Publisher> duplicatedPublisher = publisherRepositories.findByName(name);
        if (duplicatedPublisher.isPresent()) {
            throw new PublisherAlreadyExistsException(name);
        }
    }

    public  Publisher verifyAndGetIfExists(Long id){
        return  publisherRepositories.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }
    private  void checkForChangesToUpdate(Publisher foundPublisher,Publisher publisherToCreate){
        if (foundPublisher.equals(publisherToCreate)) {
            throw  new UpdateHasNoChangeException("Publisher has no changes");
        }
    }
}