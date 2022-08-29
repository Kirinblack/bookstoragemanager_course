package com.lguilherme.bookstoragemanager.models.mapper;


import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherRequestDTO;
import com.lguilherme.bookstoragemanager.models.dto.PublishersDTO.PublisherResponseDTO;
import org.mapstruct.factory.Mappers;

public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toModel(PublisherRequestDTO publisherRequestDTO);

    PublisherResponseDTO toDTO(Publisher publisher);
}
