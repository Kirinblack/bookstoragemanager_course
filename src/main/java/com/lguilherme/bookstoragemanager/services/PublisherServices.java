package com.lguilherme.bookstoragemanager.services;


import com.lguilherme.bookstoragemanager.Books.Exception.DeleteDeniedException;
import com.lguilherme.bookstoragemanager.Publisher.Exception.PublisherAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Publisher.Exception.PublisherNotFoundException;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherResponseDTO;
import com.lguilherme.bookstoragemanager.models.mapper.PublisherMapper;
import com.lguilherme.bookstoragemanager.repositories.BooksRepositories;
import com.lguilherme.bookstoragemanager.repositories.PublisherRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.util.Optional;

@Service
public class PublisherServices {

    private final static PublisherMapper publisherMapper = PublisherMapper.INSTANCE;

    private PublisherRepositories publisherRepositories;

    private BooksRepositories booksRepositories;

    @Autowired
    public PublisherServices(PublisherRepositories publisherRepositories, BooksRepositories booksRepositories){
        this.publisherRepositories = publisherRepositories;
        this.booksRepositories = booksRepositories;
    }
    public PublisherResponseDTO create(PublisherRequestDTO publisherRequestDTO) {
        verifyIfExists(publisherRequestDTO.getName());

        Publisher publisherToCreate = publisherMapper.toModel(publisherRequestDTO);
        Publisher createdPublisher = publisherRepositories.save(publisherToCreate);
        return publisherMapper.toDTO(createdPublisher);
    }

    public PublisherResponseDTO findById(Long id) {
        return publisherRepositories.findById(id)
                .map(publisherMapper::toDTO)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    public Page<PublisherResponseDTO> findAll(Pageable pageable) {
        return publisherRepositories.findAll(pageable)
                .map(publisherMapper::toDTO);
    }

    public void delete(Long id) {
        Publisher publisherToDelete = verifyAndGetIfExists(id);
        if (booksRepositories.findByPublisher(publisherToDelete).isEmpty()) {
            throw new DeleteDeniedException();
        }
        publisherRepositories.deleteById(id);
    }

    public PublisherResponseDTO update(Long id, PublisherRequestDTO publisherToUpdateDTO) {
        Publisher foundPublisher = verifyAndGetIfExists(id);
        publisherToUpdateDTO.setId(foundPublisher.getId());

        verifyIfExists(publisherToUpdateDTO.getId(), publisherToUpdateDTO.getName());

        Publisher publisherToUpdate = publisherMapper.toModel(publisherToUpdateDTO);
        Publisher updatedPublisher = publisherRepositories.save(publisherToUpdate);
        return publisherMapper.toDTO(updatedPublisher);
    }

    private void verifyIfExists(Long id, String name) {
        publisherRepositories.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));

        Optional<Publisher> samePublisher = publisherRepositories
                .findByName(name);

        if (samePublisher.isPresent() && samePublisher.get().getId() != id) {
            throw new PublisherAlreadyExistsException(name);
        }
    }

    private void verifyIfExists(String name) {
        Optional<Publisher> duplicatedPublisher = publisherRepositories
                .findByName(name);
        if (duplicatedPublisher.isPresent()) {
            throw new PublisherAlreadyExistsException(name);
        }
    }

    public PublisherRepositories verifyAndGetIfExists(Long id) {
        return publisherRepositories.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }
}