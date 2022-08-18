package com.lguilherme.bookstoragemanager.services;

import com.lguilherme.bookstoragemanager.models.Entity.publisher.entity.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.models.mapper.BooksMapper;
import com.lguilherme.bookstoragemanager.repositories.PublisherRepositories;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepositories;
import com.lguilherme.bookstoragemanager.repositories.booksRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.awt.print.Pageable;
import java.time.LocalDate;

@Service
public class BooksServices {

    private final BooksMapper booksMapper = BooksMapper.INSTANCE;

    private booksRepositories bookRepositories;

    private PublisherServices publisherServices;

    private RentalsRepositories rentalsRepositories;

    @Autowired
    public BooksServices(booksRepositories bookRepositories,PublisherServices publisherServices,RentalsRepositories rentalsRepositories){
        this.bookRepositories = bookRepositories;
        this.publisherServices = publisherServices;
        this.rentalsRepositories = rentalsRepositories;
    }
    public BooksResponseDTO create(BooksRequestDTO booksRequestDTO){
        verifyIfExists(booksRequestDTO.getName());
        Publisher foundPublisher = publisherServices.verifyAndGetIfExists(booksRequestDTO.getPublisherId());
        validateDate(BooksRequestDTO);

        Book bookToSave = booksMapper.toModel(booksRequestDTO);
        bookToSave.setPublisher(foundPublisher);

        Book savedBook =  bookRepositories.save(bookToSave);
        return booksMapper.toDTO(savedBook);

    }
    public BooksResponseDTO findById(Long id) {
        return booksRepositories.findById(id)
                .map(booksMapper::toDTO)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    public Page<BooksResponseDTO> findAll(Pageable pageable) {
        return booksRepositories.findAll(pageable)
                .map(booksMapper::toDTO);
    }

    public void delete(Long id) {
        Book bookToDelete = verifyAndGetIfExists(id);
        if(RentalsRepositories.findByBook(bookToDelete).isPresent()) throw new DeleteDeniedException();
        booksRepositories.deleteById(id);
    }

    public BooksResponseDTO update(Long id, BooksRequestDTO bookRequestDTO) {
        Book foundBook = verifyAndGetIfExists(id);
        Publisher foundPublisher = PublisherServices.verifyAndGetIfExists(bookRequestDTO.getPublisherId());
        validateDate(bookRequestDTO);

        Book bookToUpdate = booksMapper.toModel(bookRequestDTO);
        bookToUpdate.setId(id);
        bookToUpdate.setPublisher(foundPublisher);
        bookToUpdate.setLaunchDate(foundBook.getLaunchDate());
        Book updatedBook = booksRepositories.save(bookToUpdate);
        return booksMapper.toDTO(updatedBook);
    }

    private void verifyIfExists(String name) {
    }
}

    public Book verifyAndGetIfExists(Long id) {
        return booksRepositories.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    private void validateDate(BooksRequestDTO bookRequestDTO) {
        LocalDate today = LocalDate.now();
        if(bookRequestDTO.getLaunchDate().isAfter(today))
            throw new InvalidDateException();
    }
}
