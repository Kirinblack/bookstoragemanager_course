package com.lguilherme.bookstoragemanager.author.entity;
import lombok.Data;
import javax.persistence.*;

@Data
@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "Integer default 0")
    private int age;

}
