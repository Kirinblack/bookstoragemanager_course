package com.lguilherme.bookstoragemanager.repositories;


import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.Entity.rentals.rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;



public interface RentalsRepository extends JpaRepository<rentals, Long> {


    List<rentals> findByBookandUsers(Books books , Users users);

    List<rentals> findByBook(Books book);

    Optional<rentals> findByUsers(Users user);



}