package com.lguilherme.bookstoragemanager.models.Entity.rentals;



import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import lombok.*;
import javax.persistence.*;
import java.time.LocalDate;


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
