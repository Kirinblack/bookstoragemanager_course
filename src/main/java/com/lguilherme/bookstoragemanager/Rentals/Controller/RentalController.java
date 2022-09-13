package com.lguilherme.bookstoragemanager.Rentals.Controller;

import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import com.lguilherme.bookstoragemanager.services.RentalsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/rentals")
public class RentalController implements  RentalControllerDocs{

    @Autowired
   RentalsServices rentalsServices;

    @Override
    public RentalResponseDTO create(RentalRequestDTO rentalsRequestDTO) {
        return null;
    }

    @Override
    public RentalResponseDTO findById(@PathVariable Long id){
        return rentalsServices.findById(id);
    }

    @Override
    public List<RentalResponseDTO> getRentals() {
        return null;
    }

    @Override
    public Page<RentalResponseDTO> getRentals(Pageable pageable) {
        return (Page<RentalResponseDTO>) rentalsServices.findAll();
    }


    @Override
    public void delete(Long id) {

    }
}
