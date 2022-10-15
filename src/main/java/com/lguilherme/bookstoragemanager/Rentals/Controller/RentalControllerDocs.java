package com.lguilherme.bookstoragemanager.Rentals.Controller;

import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RentalControllerDocs {
    @ApiOperation(value = "Create a new book")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success Rentals creation"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    RentalResponseDTO create(RentalRequestDTO rentalsRequestDTO);

    @ApiOperation(value = "Find a result by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success search"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    RentalResponseDTO findById(Long id);

    @ApiOperation(value = "Get all rentals")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "success Rentals creation"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    List<RentalResponseDTO> getRentals();

    Page<RentalResponseDTO> getRentals(Pageable pageable);

    @ApiOperation(value = "Delete a rental by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, id deleted"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    void delete(Long id);
}