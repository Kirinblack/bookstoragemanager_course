package com.lguilherme.bookstoragemanager.Users.Controller;

import com.lguilherme.bookstoragemanager.models.dto.UserDTO.AuthenticatedUser;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.UserDTO;
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
    private final UsersServices usersServices;

    @Autowired
    public UserController(UsersServices usersServices) {
        this.usersServices = usersServices;
    }

    @GetMapping
    public List<UserDTO> getUsers() {
        return usersServices.findAll();
    }

    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id) {
        return usersServices.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody @Valid UserDTO userToCreateDTO) {
        return usersServices.create(userToCreateDTO);
    }

    @Override
    public MessageDTO update(Long id, AuthenticatedUser authenticatedUser, UserDTO userToUpdateDTO) {
        return null;
    }

    @Override
    public Page<UserDTO> findAll(Pageable pageable) {
        return null;
    }

    @PutMapping("{id}")
    public UserDTO update(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) {
        return usersServices.update(id, userDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        usersServices.deleteById(id);
    }
}
