package com.lguilherme.bookstoragemanager.controller.books;

import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.BooksDTO.BooksResponseDTO;
import com.lguilherme.bookstoragemanager.services.BooksServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController implements BooksControllerDocs {



    private BooksServices bookService;

    @Autowired
    public BooksController(BooksServices booksServices){
        this.BooksServices = booksServices;

    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BooksResponseDTO create(@RequestBody @Valid BooksRequestDTO BooksRequestDTO){
        return booksServices.create(BooksRequestDTO);
        
    }
    @GetMapping("/{id}")
    public BooksResponseDTO findById(@PathVariable Long id){
      return booksServices.findById(id);

    }
    @GetMapping
    public List<BooksResponseDTO> findAll(){
        return booksServices.findAll();

    }
   @DeleteMapping("/{id}")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   public void delete(@PathVariable Long id){
        booksServices.delete(id);

   }
  @PutMapping("/{id}")
    public BooksResponseDTO update(@PathVariable Long id,@RequestBody @Valid BooksRequestDTO booksRequestDTO){
        return booksServices.update(id, booksRequestDTO);

  }

}
