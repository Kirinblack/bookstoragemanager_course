package com.lguilherme.bookstoragemanager.Books.Controller;


import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Api("Books module management")
public interface BooksControllerDocs {
    @ApiOperation(value = "Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucess book creation"),
            @ApiResponse(code = 400, message = "Missing required fields,wrong field range value or book already registered on system")
    })
    MessageDTO create(BooksRequestDTO booksRequestDTO);

    @ApiOperation(value = "Find book by id operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200,message = "the book was successfully found"),
            @ApiResponse(code = 404, message = "Book not found error")
    })
    BooksResponseDTO findById(Long id);

    @ApiOperation(value = "List all books operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success book list found")
    })
    Page<BooksResponseDTO> findAll(Pageable pageable);

    @ApiOperation(value = "Book delete operation")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "The book was successfully deleted"),
            @ApiResponse(code = 404, message = "Book not found error")
    })
    void delete(Long id);

    @ApiOperation(value = "Book update operation")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success book updated"),
            @ApiResponse(code = 400, message = "Missing required field, or an error on validation field rules")
    })
    MessageDTO update(Long id, BooksRequestDTO bookRequestDTO);

}
