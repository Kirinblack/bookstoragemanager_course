package com.lguilherme.bookstoragemanager.repositories;


import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.rentals.rentals;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface RentalsRepositories extends JpaRepository<rentals, Long> {


    List<rentals> findByBookandUsers(Object book, Object user);

    List<rentals> findByBook(Books book);

    Optional<rentals> findByUsers(Users user);

    Optional<rentals> findByIdAndUsers(Long id, Users user);

    Page<rentals> findAllByUsers(Users user, Pageable pageable);

}