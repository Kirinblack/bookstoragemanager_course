package com.lguilherme.bookstoragemanager.Books.Controller;


import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.services.BooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.print.Pageable;


@RestController
@RequestMapping("/api/v1/books")
public abstract class BooksController implements BooksControllerDocs {


    private final BooksServices BooksServices;
    private com.lguilherme.bookstoragemanager.services.BooksServices bookService;

    @Autowired
    public BooksController(BooksServices booksServices){
        this.BooksServices = booksServices;

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BooksResponseDTO create(@RequestBody @Valid BooksRequestDTO BooksRequestDTO){
        return bookService.create(BooksRequestDTO);
        
    }
    @GetMapping("/{id}")
    public BooksResponseDTO findById(@PathVariable Long id){
      return bookService.findById(id);

    }

    @Override
    public Page<BooksResponseDTO> findAll(Pageable pageable) {
        return null;
    }

   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void delete(@PathVariable Long id){
        bookService.delete(id);

   }
  @PutMapping("/{id}")
    public BooksResponseDTO update(@PathVariable Long id,@RequestBody @Valid BooksRequestDTO booksRequestDTO){
        return bookService.update(id, booksRequestDTO);

  }

}
