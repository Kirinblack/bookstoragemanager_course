package com.lguilherme.bookstoragemanager.Users.entity;

import javax.persistence.*;

@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 100)
    private String  address;

    public Users() {
    }
}
