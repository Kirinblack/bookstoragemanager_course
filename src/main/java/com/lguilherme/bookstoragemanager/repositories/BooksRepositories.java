package com.lguilherme.bookstoragemanager.repositories;


import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;
import java.util.stream.DoubleStream;

public interface BooksRepositories extends JpaRepository<Books, Long> {

    static DoubleStream findAll(Pageable pageable) {
        return null;
    }

    Optional<Book> findByName(String name);

    List<Book> findByPublisher(Publisher publisher);

    default Optional<Books> findById(Long id) {
        return null;
    }


}
