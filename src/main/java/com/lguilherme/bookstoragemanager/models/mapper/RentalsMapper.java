package com.lguilherme.bookstoragemanager.models.mapper;


import com.lguilherme.bookstoragemanager.models.Entity.rentals.rentals;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.RentalDTO.RentalResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RentalsMapper {

    RentalsMapper INSTANCE = Mappers.getMapper(RentalsMapper.class);

    rentals toModel(RentalRequestDTO rentalRequestDTO);

    rentals toModel(RentalResponseDTO rentalResponseDTO);

    RentalResponseDTO toDTO(rentals rental);

}
