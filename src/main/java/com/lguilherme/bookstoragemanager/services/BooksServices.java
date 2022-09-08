package com.lguilherme.bookstoragemanager.services;


import com.lguilherme.bookstoragemanager.Books.Exception.BookAlreadyExistsException;
import com.lguilherme.bookstoragemanager.Books.Exception.BookNotFoundException;
import com.lguilherme.bookstoragemanager.Books.Exception.DeleteDeniedException;
import com.lguilherme.bookstoragemanager.Books.Exception.InvalidDateException;
import com.lguilherme.bookstoragemanager.Exception.UpdateHasNoChangeException;
import com.lguilherme.bookstoragemanager.Utils.StringPatterns;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import com.lguilherme.bookstoragemanager.models.mapper.BooksMapper;
import com.lguilherme.bookstoragemanager.repositories.BooksRepositories;
import com.lguilherme.bookstoragemanager.repositories.RentalsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BooksServices {

    private final BooksMapper booksMapper = BooksMapper.INSTANCE;

    private BooksRepositories bookRepositories;

    private PublisherServices publisherServices;

    private RentalsRepositories rentalsRepositories;

    private RentalsServices rentalsServices;

    private StringPatterns stringPatterns;

    @Autowired
    @Lazy
    public BooksServices( BooksRepositories bookRepositories,PublisherServices publisherServices, RentalsRepositories rentalsRepositories, RentalsServices rentalsServices){
        this.bookRepositories = bookRepositories;
        this.publisherServices = publisherServices;
        this.rentalsRepositories = rentalsRepositories;
        this.rentalsServices = rentalsServices;
        this.stringPatterns = stringPatterns;

    }
    public  Page<BooksResponseDTO> findAll(Pageable pageable) {
        return  bookRepositories.findAll(pageable)
                .map(booksMapper::ToDTO);
    }

    public  BooksResponseDTO findById(Long id) {
        return bookRepositories.findById(id)
                .map(booksMapper::ToDTO)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public MessageDTO create(BooksRequestDTO booksRequestDTO){
        booksRequestDTO.setAuthor(stringPatterns.normalize(booksRequestDTO.getAuthor()));
        booksRequestDTO.setName(stringPatterns.normalize(booksRequestDTO.getName()));

        verifyIfExists(booksRequestDTO.getId(), booksRequestDTO.getName(), booksRequestDTO.getCode());

        Publisher foundPublisher = publisherServices.verifyAndGetIfExists(booksRequestDTO.getPublisherId());
        Books bookToCreate = BooksMapper.toModel(booksRequestDTO);
        bookToCreate.setRelease(LocalDate.now());
        bookToCreate.setChangeDate(LocalDate.now());
        bookToCreate.setPublisher(foundPublisher);
        Books createdBook = bookRepositories.save(bookToCreate);

        String createdMessage = String .format("Book $s with id %d was created successfully!!", createdBook.getName(), createdBook.getId());

        return  MessageDTO.builder()
                .message(createdMessage)
                .build();
      }

      public MessageDTO update(Long id, BooksRequestDTO booksRequestDTO) {
        booksRequestDTO.setName(stringPatterns.normalize(booksRequestDTO.getName()));
        booksRequestDTO.setAuthor(stringPatterns.normalize(booksRequestDTO.getAuthor()));

        Books foundBook = verifyAndGetIfExists(id);
        Publisher foundPublisher = publisherServices.verifyAndGetIfExists(booksRequestDTO.getPublisherId());

        verifyIfTheNameChanged(foundBook.getName(),booksRequestDTO.getName());

        booksRequestDTO.setId(foundBook.getId());
        booksRequestDTO.setCode(foundBook.getCode());

        Books bookToCreate = BooksMapper.toModel(booksRequestDTO);

        bookToCreate.setPublisher(foundPublisher);
        bookToCreate.setRelease((foundBook.getRelease()));
        bookToCreate.setChangeDate(foundBook.getChangeDate());
        checkForChangesToUpdate(foundBook, bookToCreate);
        bookToCreate.setChangeDate(LocalDate.now());

        Books createdBook = bookRepositories.save(bookToCreate);

        String createdMessage = String.format("Book with id %d has been updated successfully!!",createdBook.getId());

        return MessageDTO.builder()
                .message(createdMessage)
                .build();
      }

      private  void checkForChangesToUpdate(Books foundBook, Books newBook) {
        if (foundBook.equals(newBook)){
            throw new UpdateHasNoChangeException("Book has no changes");
        }
      }
    private void verifyIfTheNameChanged(String oldName, String newName) {
        if (!oldName.equals(newName))
            verifyIfExists(newName);
     }
    private  void verifyIfExists(String name) {
        Optional<Books> foundBook = bookRepositories.findByName(name);
        if (foundBook.isPresent()){
            throw  new BookAlreadyExistsException(name);
        }
    }
    public  Books verifyAndGetIfExists(Long id) {
        return bookRepositories.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public  void deleteById(Long id) {

        bookRepositories.deleteById(id);
        rentalsServices.deleteByBook(id);
    }


}
