package com.lguilherme.bookstoragemanager.models.Entity.rentals.entity;

import com.lguilherme.bookstoragemanager.models.Entity.Users.entity.Users;
import com.lguilherme.bookstoragemanager.models.Entity.books.entity.Books;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.awt.print.Book;
import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class rentals {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate rentalDate;

    @Column(nullable = false)
    private LocalDate returnForecast;

    @Column
    private String returnDate;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
    }
