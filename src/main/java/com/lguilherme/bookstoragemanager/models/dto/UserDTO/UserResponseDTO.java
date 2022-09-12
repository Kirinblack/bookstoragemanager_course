package com.lguilherme.bookstoragemanager.models.dto.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private  Long id;

    private  String name;

    private String email;

    private String password;

    private String city;

    private  String address;

    private LocalDate registrationDate;

    private String role;
}
