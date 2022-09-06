package com.lguilherme.bookstoragemanager.services;


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
        validateDate(booksRequestDTO);

        Books bookToSave = BooksMapper.toModel(booksRequestDTO);
        bookToSave.setPublisher(foundPublisher);

        Books savedBook =  BooksRepositories.save(bookToSave);
        return BooksMapper.ToDTO(savedBook);

    }
    public BooksResponseDTO findById(Long id) {
        return BooksRepositories.findById(id)
                .map(BooksMapper::ToDTO)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    public Page<BooksResponseDTO> findAll(Pageable pageable) {
        return BooksRepositories.findById(pageable)
                .map(BooksMapper::ToDTO);
    }

    public void delete(Long id) {
        Books bookToDelete = (Books) verifyAndGetIfExists(id);
        if(RentalsRepositories.findByBook(bookToDelete).isPresent()) throw new DeleteDeniedException();
        Optional<Books> bookModel = BooksRepositories.findById(id);
        bookRepositories.delete(bookModel.get());
    }

    public BooksResponseDTO update(Long id, BooksRequestDTO bookRequestDTO) {
        Books foundBook = (Books) verifyAndGetIfExists(id);
        Publisher foundPublisher = PublisherServices.verifyAndGetIfExists(BooksRequestDTO.getPublisherId());
        validateDate(bookRequestDTO);

        Books bookToUpdate = BooksMapper.toModel(bookRequestDTO);
        bookToUpdate.setId(id);
        bookToUpdate.setPublisher(foundPublisher);
        bookToUpdate.setLaunchDate(foundBook.getLaunchDate());
        Books updatedBook = BooksRepositories.save(bookToUpdate);
        return BooksMapper.ToDTO(updatedBook);
    }

    private void verifyIfExists(String name) {
    }
}

    public Books verifyAndGetIfExists(Long id) {
        return (BooksRepositories) BooksRepositories.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }
    private void validateDate(BooksRequestDTO bookRequestDTO) {
        LocalDate today = LocalDate.now();
        if(bookRequestDTO.getLaunchDate().isAfter(today))
            throw new InvalidDateException();
    }

}
