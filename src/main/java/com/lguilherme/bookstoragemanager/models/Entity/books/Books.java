package com.lguilherme.bookstoragemanager.models.Entity.books;

import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "Books")
public class Books  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 100)
    private String name;

    @Column(nullable = false)
    private  String code;

    @Column(columnDefinition =  "integer default 0")
    private int quantity;

    @Column(columnDefinition =  "integer default 0")
    private int requentQuantity;

    @Column(nullable = false)
    private LocalDate release;

    @Column(nullable = false)
    private LocalDate changeDate;

    @Column(nullable = false)
    private LocalDate launchDate;

    @Column(nullable = false)
    private String author;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Publisher publisher;
}
