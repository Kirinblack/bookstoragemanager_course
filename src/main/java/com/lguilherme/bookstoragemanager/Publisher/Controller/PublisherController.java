package com.lguilherme.bookstoragemanager.Publisher.Controller;

import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherResponseDTO;
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

    private PublisherServices publisherServices;

    @Autowired
    public PublisherController(PublisherServices publisherServices){
        this.publisherServices = publisherServices;
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PublisherResponseDTO create(@RequestBody @Valid PublisherRequestDTO publisherRequestDTO){
        return publisherServices.create(publisherRequestDTO);
    }
    @GetMapping("/{id}")
    public PublisherResponseDTO findById(@PathVariable Long id){
        return publisherServices.findById(id);
    }

    @Override
    public Page<PublisherResponseDTO> findAll(java.awt.print.Pageable pageable) {
        return null;
    }

    @GetMapping
    public Page<PublisherResponseDTO> findAll(Pageable pageable){
        return publisherServices.findAll((java.awt.print.Pageable) pageable);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        publisherServices.delete(id);
    }

    @PutMapping("/{id}")
    public PublisherResponseDTO update(@PathVariable Long id, @RequestBody @Valid PublisherRequestDTO publisherRequestDTO){
        return  publisherServices.update(id, publisherRequestDTO);
    }

}
