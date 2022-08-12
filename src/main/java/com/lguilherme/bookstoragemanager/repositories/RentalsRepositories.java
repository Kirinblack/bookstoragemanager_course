package com.lguilherme.bookstoragemanager.repositories;

import com.lguilherme.bookstoragemanager.models.Entity.Users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;
import java.util.List;
import java.util.Optional;

public interface RentalsRepositories extends JpaRepository<RentalsRepositories, Long> {

    List<RentalsRepositories> findByBookandUsers(Object book,Object user);

    Optional<RentalsRepositories> findByBook(Book book);

    Optional<RentalsRepositories> findByUsers(Users users);
}
