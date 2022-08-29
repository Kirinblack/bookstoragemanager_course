package com.lguilherme.bookstoragemanager.services;

import com.lguilherme.bookstoragemanager.Books.DTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.Books.Exception.BookNotFoundException;
import com.lguilherme.bookstoragemanager.Books.Exception.DeleteDeniedException;
import com.lguilherme.bookstoragemanager.Books.Exception.InvalidDateException;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.models.mapper.BooksMapper;
import com.lguilherme.bookstoragemanager.repositories.BooksRepositories;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.awt.print.Pageable;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class BooksServices {

    private final BooksMapper booksMapper = BooksMapper.INSTANCE;

    private BooksRepositories bookRepositories;

    private PublisherServices publisherServices;

    private RentalsRepositories rentalsRepositories;

    @Autowired
    public BooksServices( BooksRepositories bookRepositories,PublisherServices publisherServices, RentalsRepositories rentalsRepositories){
        this.bookRepositories = bookRepositories;
        this.publisherServices = publisherServices;
        this.rentalsRepositories = rentalsRepositories;
    }
    public BooksResponseDTO create(BooksRequestDTO booksRequestDTO){
        verifyIfExists(booksRequestDTO.getName());
        Publisher foundPublisher = publisherServices.verifyAndGetIfExists(BooksRequestDTO.getPublisherId());
        validateDate(BooksRequestDTO);

        Book bookToSave = BooksMapper.toModel(BooksRequestDTO);
        bookToSave.setPublisher(foundPublisher);

        Book savedBook =  BooksRepositories.save(bookToSave);
        return BooksMapper.toDTO(savedBook);

    }
    public BooksResponseDTO findById(Long id) {
        return BooksRepositories.findById(id)
                .map(BooksMapper::toDTO)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    public Page<BooksResponseDTO> findAll(Pageable pageable) {
        return BooksRepositories.findAll(pageable)
                .map(BooksMapper::toDTO);
    }

    public void delete(Long id) {
        Book bookToDelete = (Books) verifyAndGetIfExists(id);
        if(RentalsRepositories.findByBook(bookToDelete).isPresent()) throw new DeleteDeniedException();
        Optional<Books> bookModel = bookRepositories.findById(id);
        bookRepositories.delete(bookModel.get());
    }

    public BooksResponseDTO update(Long id, BooksRequestDTO bookRequestDTO) {
        Book foundBook = (Book) verifyAndGetIfExists(id);
        Publisher foundPublisher = PublisherServices.verifyAndGetIfExists(BooksRequestDTO.getPublisherId());
        validateDate(bookRequestDTO);

        Book bookToUpdate = BooksMapper.toModel(bookRequestDTO);
        bookToUpdate.setId(id);
        bookToUpdate.setPublisher(foundPublisher);
        bookToUpdate.setLaunchDate(foundBook.getLaunchDate());
        Book updatedBook = BooksRepositories.save(bookToUpdate);
        return BooksMapper.toDTO(updatedBook);
    }

    private void verifyIfExists(String name) {
    }
}

    public BooksRepositories verifyAndGetIfExists(Long id) {
        return (BooksRepositories) BooksRepositories.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    private void validateDate(BooksRequestDTO bookRequestDTO) {
        LocalDate today = LocalDate.now();
        if(bookRequestDTO.getLaunchDate().isAfter(today))
            throw new InvalidDateException();
    }

}
