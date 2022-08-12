package com.lguilherme.bookstoragemanager.models.Entity.books.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TB Books")
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

}
