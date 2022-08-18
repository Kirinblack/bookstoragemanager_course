package com.lguilherme.bookstoragemanager.models.mapper;

import com.lguilherme.bookstoragemanager.models.Entity.books.entity.Books;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import org.mapstruct.factory.Mappers;

import java.awt.print.Book;

public interface BooksMapper {

    BooksMapper INSTANCE = Mappers.getMapper(BooksMapper.class);

    Books toModel(BooksRequestDTO bookRequestDTO);

    Books toModel(BooksResponseDTO bookResponseDTO);

    BooksResponseDTO toDTO(Book book);
}
