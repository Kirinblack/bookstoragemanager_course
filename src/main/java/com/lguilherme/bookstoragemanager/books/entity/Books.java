package com.lguilherme.bookstoragemanager.books.entity;

import com.lguilherme.bookstoragemanager.publisher.entity.Publisher;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(columnDefinition =  "integer default 0")
    private int quantity;

    @Column(columnDefinition =  "integer default 0")
    private int requentQuantity;

    @Column(nullable = false)
    private LocalDate launchDate;

    @Column(nullable = false)
    private String author;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Publisher publisher;

}
