package com.lguilherme.bookstoragemanager.repositories;

import com.lguilherme.bookstoragemanager.models.Entity.publisher.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface booksRepositories extends JpaRepository<booksRepositories, Long> {
    Optional<Book> findByName(String name);

    Optional<Book> findByPublisher(Publisher publisher);

}
