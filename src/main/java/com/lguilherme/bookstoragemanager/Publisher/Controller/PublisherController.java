package com.lguilherme.bookstoragemanager.Publisher.Controller;

import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherResponseDTO;
import com.lguilherme.bookstoragemanager.models.dto.UserDTO.MessageDTO;
import com.lguilherme.bookstoragemanager.services.PublisherServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/publisher")
@CrossOrigin(origins = "*")
public class PublisherController implements PublisherControllerDocs {


    @Autowired
    PublisherServices publisherServices;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDTO create(@RequestBody @Valid PublisherRequestDTO publisherRequestDTO){
        return publisherServices.create(publisherRequestDTO);
    }
    @GetMapping("/{id}")
    public PublisherResponseDTO findById(@PathVariable Long id){
        return publisherServices.findById(id);
    }



    @GetMapping
    public Page<PublisherResponseDTO> findAll(Pageable pageable) {
        return publisherServices.findAll(pageable);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        publisherServices.delete(id);
    }

    @PutMapping("/{id}")
    public MessageDTO update(@PathVariable Long id, @RequestBody @Valid PublisherRequestDTO publisherRequestDTO){
        return  publisherServices.update(id, publisherRequestDTO);
    }

}
