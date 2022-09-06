package com.lguilherme.bookstoragemanager.Rentals.Controller;

import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import com.lguilherme.bookstoragemanager.services.RentalsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/rentals")
public class RentalController implements  RentalControllerDocs{

    private final RentalsServices rentalsServices;

    @Autowired
    public RentalController(RentalsServices rentalsServices){
        this.rentalsServices = rentalsServices;
    }

    @Override
    public RentalResponseDTO create(RentalRequestDTO rentalsRequestDTO) {
        return null;
    }

    @Override
    public RentalResponseDTO findById(Long id) {
        return null;
    }

    @Override
    public List<RentalResponseDTO> getRentals() {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
