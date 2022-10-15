package com.lguilherme.bookstoragemanager.Users.Controller;


import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserResponseDTO;
import com.lguilherme.bookstoragemanager.services.UsersServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*")
public abstract class UserController implements  UserControllerDocs {

    @Autowired
   UsersServices usersServices;

    @GetMapping
    public List<UserResponseDTO> getUsers() {
        return usersServices.findAll();
    }

    @GetMapping("/{id}")
    public UserResponseDTO getById(@PathVariable Long id) {
        return usersServices.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO create(@RequestBody @Valid UserRequestDTO userToCreateDTO) {
        return usersServices.create(userToCreateDTO);
    }

    //@Override
    //public MessageDTO update(Long id, UserRequestDTO userToUpdateDTO) {
        //return null;
   // }

    @Override
    public Page<UserRequestDTO> findAll(Pageable pageable) {
        return null;
    }

   // @PutMapping("{id}")
    //public UserResponseDTO update(@PathVariable Long id, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        //return usersServices.update(id, userRequestDTO);
   // }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usersServices.deleteById(id);
    }
}
