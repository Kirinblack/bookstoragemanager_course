package com.lguilherme.bookstoragemanager.Users.Controller;



import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserResponseDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

@Api("System users management")
public interface UserControllerDocs {
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, message realized"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    @ApiOperation(value = "Create a new message")
    UserResponseDTO create(UserRequestDTO userToCreateDTO);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success to get all users"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    @ApiOperation(value = "Get all users")
    List<UserResponseDTO> getUsers();

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success to get the user"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    @ApiOperation(value = "Get user by id")
    UserResponseDTO getById(Long id);


    //MessageDTO update(Long id, UserRequestDTO userToUpdateDTO);

    Page<UserRequestDTO> findAll(Pageable pageable);

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    @ApiOperation(value = "Update a user")
    public UserResponseDTO update(Long id, UserRequestDTO userRequestDTO);


    @ApiOperation(value = "Delete a user by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success, id deleted"),
            @ApiResponse(code = 400, message = "Missing data. Check and try again.")
    })
    void delete(Long id);
}
