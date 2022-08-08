package com.lguilherme.bookstoragemanager.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping{"/api/v1/books"}
public class BookController {
    @ApiOperation(value = "Return sn exemple hello world")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message ="Sucess method return" )
    }
    )

    @GetMapping
    public String hello ()
    {
        return "Hello Bookstore Manager";


    }
}
