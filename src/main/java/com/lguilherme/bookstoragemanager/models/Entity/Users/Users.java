package com.lguilherme.bookstoragemanager.models.Entity.Users;



import com.lguilherme.bookstoragemanager.Users.enums.Role;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name="Users")
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
    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

}
