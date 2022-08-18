package com.lguilherme.bookstoragemanager.controller.books;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Books module management")
public interface BooksControllerDocs {
    @ApiOperation(value = "Book creation operation")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Sucess book creation"),
            @ApiResponse(code = 400, message = "Missing required fields,wrong field range value or book already registered on system")
    })

}
