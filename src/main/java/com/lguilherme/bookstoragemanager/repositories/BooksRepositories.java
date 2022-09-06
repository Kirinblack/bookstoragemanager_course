package com.lguilherme.bookstoragemanager.repositories;


import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;



@Repository
public interface BooksRepositories extends JpaRepository<Books, Long> {

    default Optional<Books> findById(Long id) {
        return null;
    }

    Optional<Books> findByName(String name);


    List<Books> findByPublisher(Publisher publisher);
}



