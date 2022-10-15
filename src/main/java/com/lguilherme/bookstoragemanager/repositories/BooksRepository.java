package com.lguilherme.bookstoragemanager.repositories;


import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;




public interface BooksRepository extends JpaRepository<Books, Long> {

    Optional<Books> findById(Long id);

    Optional<Books> findByName(String name);


    List<Books> findByPublisher(Publisher publisher);

    Optional<Books> findByIdOrNameOrCode(Long id, String name, String code);
}



