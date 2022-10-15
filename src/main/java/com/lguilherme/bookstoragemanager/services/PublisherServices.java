package com.lguilherme.bookstoragemanager.services;


import com.lguilherme.bookstoragemanager.Exception.UpdateHasNoChangeException;
import com.lguilherme.bookstoragemanager.Publisher.Exception.PublisherAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Publisher.Exception.PublisherNotFoundException;
import com.lguilherme.bookstoragemanager.Utils.StringPatterns;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import com.lguilherme.bookstoragemanager.models.mapper.PublisherMapper;
import com.lguilherme.bookstoragemanager.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class PublisherServices {
    private static final PublisherMapper publisherMapper = PublisherMapper.INSTANCE;
    @Autowired
    @Lazy
    PublisherRepository publisherRepository;

    BooksServices booksServices;

    StringPatterns stringPatterns;

    public Page<PublisherResponseDTO> findAll(Pageable pageable){
        return publisherRepository.findAll(pageable)
                .map(publisherMapper::toDTO);
    }

    public PublisherResponseDTO findById(Long id){
        return publisherRepository.findById(id)
                .map(publisherMapper::toDTO)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }

    public MessageDTO create(PublisherRequestDTO publisherRequestDTO){
        verifyIfExists(publisherRequestDTO.getName(), publisherRequestDTO.getCode());

        Publisher publisherToCreate = publisherMapper.toModel(publisherRequestDTO);
        publisherToCreate.setRegistrationDate(LocalDate.now());
        Publisher createdPublisher = publisherRepository.save(publisherToCreate);

        String createdMessage = String.format("Publisher %s with id %s successfully created!!",createdPublisher.getName(),createdPublisher.getId());
        return  MessageDTO.builder()
                .message(createdMessage)
                .build();
    }

    public MessageDTO update(Long id, PublisherRequestDTO publisherRequestDTO){
        Publisher foundPublisher = verifyAndGetIfExists(id);
        publisherRequestDTO.setId(foundPublisher.getId());
        Publisher publisherToCreate = publisherMapper.toModel(publisherRequestDTO);
        publisherToCreate.setRegistrationDate(foundPublisher.getRegistrationDate());
        checkForChangesToUpdate(foundPublisher, publisherToCreate);
        Publisher createdPublisher = publisherRepository.save(publisherToCreate);

        String createdMessage = String.format("Publisher with id %d has been updated successfully!!",createdPublisher.getId());

        return MessageDTO.builder()
                .message(createdMessage)
                .build();
    }

    private void verifyIfExists(String name,String code) {
        Optional<Publisher> duplicatedPublisher = publisherRepository.findByName(name);
        if (duplicatedPublisher.isPresent()) {
            throw new PublisherAlreadyExistsException(name);
        }
    }

    public Publisher verifyAndGetIfExists(Long id){
        return  publisherRepository.findById(id)
                .orElseThrow(() -> new PublisherNotFoundException(id));
    }
    private  void checkForChangesToUpdate(Publisher foundPublisher,Publisher publisherToCreate){
        if (foundPublisher.equals(publisherToCreate)) {
            throw  new UpdateHasNoChangeException("Publisher has no changes");
        }
    }

    public void delete(Long id) {
    }
}