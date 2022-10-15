package com.lguilherme.bookstoragemanager.Books.Controller;


import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import com.lguilherme.bookstoragemanager.services.BooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
public abstract class BooksController implements BooksControllerDocs {

    private com.lguilherme.bookstoragemanager.services.BooksServices bookService;

    @Autowired
    BooksServices booksServices;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO create(@RequestBody @Valid BooksRequestDTO BooksRequestDTO){
        return booksServices.create(BooksRequestDTO);
        
    }
    @GetMapping("/{id}")
    public BooksResponseDTO findById(@PathVariable Long id){
      return booksServices.findById(id);

    }

    @Override
    public Page<BooksResponseDTO> findAll(Pageable pageable) {
        return null;
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void delete(@PathVariable Long id){
        booksServices.deleteById(id);

   }
  @PutMapping("/{id}")
    public MessageDTO update(@PathVariable Long id, @RequestBody @Valid BooksRequestDTO booksRequestDTO){
        return booksServices.update(id, booksRequestDTO);

  }

}
