package com.lguilherme.bookstoragemanager.models.dto.RentaltDTO;

import com.lguilherme.bookstoragemanager.models.Entity.Users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalResponseDTO {
    private Long id;

    private Book book;

    private Users users;

    private LocalDate rentalDate;

    private LocalDate returnForecast;

    private String returnDate;
}
