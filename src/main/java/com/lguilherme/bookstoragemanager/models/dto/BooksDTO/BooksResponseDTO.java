package com.lguilherme.bookstoragemanager.models.dto.BooksDTO;

import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherRequestDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BooksResponseDTO {

    private Long id;

    private String name;

    private Integer quantity;

    private Integer rentedQuantity;

    private LocalDate launchDate;

    private String author;

    private PublisherRequestDTO publisher;
}
