package com.lguilherme.bookstoragemanager.models.mapper;

import com.lguilherme.bookstoragemanager.models.Entity.publisher.entity.Publisher;
import com.lguilherme.bookstoragemanager.models.dto.PublisherDTO;
import org.mapstruct.factory.Mappers;

public interface PublisherMapper {

    PublisherMapper INSTANCE = Mappers.getMapper(PublisherMapper.class);

    Publisher toModel(PublisherDTO publisherDTO);

    PublisherDTO toDTO(Publisher publisher);
}
