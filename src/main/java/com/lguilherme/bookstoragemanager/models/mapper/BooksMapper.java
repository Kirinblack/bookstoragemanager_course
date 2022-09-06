package com.lguilherme.bookstoragemanager.models.mapper;

import com.lguilherme.bookstoragemanager.models.Entity.books.Books;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import org.mapstruct.factory.Mappers;

import java.awt.print.Book;

public interface BooksMapper {

    BooksMapper INSTANCE = Mappers.getMapper(BooksMapper.class);

     static Books toModel(BooksRequestDTO bookRequestDTO) {
        return null;
    }

    Books toModel(BooksResponseDTO bookResponseDTO);

    static BooksResponseDTO ToDTO(Books books) {
        return null;
    }
}
