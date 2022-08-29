package com.lguilherme.bookstoragemanager.Rentals.Controller;

import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestUpdateDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.AuthenticatedUser;
import com.lguilherme.bookstoragemanager.services.RentalsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("api/v1/rentals")
public class RentalController implements  RentalControllerDocs{

    private RentalsServices rentalsServices;

    @Autowired
    public RentalController(RentalsServices rentalsServices){
        this.rentalsServices = rentalsServices;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RentalResponseDTO create(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, @RequestBody @Valid RentalRequestDTO rentalRequestDTO) {
        return rentalsServices.create(authenticatedUser, rentalRequestDTO);
    }

    @GetMapping("/{id}")
    public RentalResponseDTO findById(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        return rentalsServices.findById(id, authenticatedUser);
    }

    @GetMapping
    public Page<RentalResponseDTO> findAll(@AuthenticationPrincipal AuthenticatedUser authenticatedUser, Pageable pageable) {
        return rentalsServices.findAll(authenticatedUser, pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser authenticatedUser) {
        rentalsServices.delete(id, authenticatedUser);
    }

    @PutMapping("/{id}")
    public RentalResponseDTO update(@PathVariable Long id, @AuthenticationPrincipal AuthenticatedUser authenticatedUser, @RequestBody @Valid RentalRequestUpdateDTO rentalRequestUpdateDTO) {
        return rentalsServices.update(id, authenticatedUser, rentalRequestUpdateDTO);
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
