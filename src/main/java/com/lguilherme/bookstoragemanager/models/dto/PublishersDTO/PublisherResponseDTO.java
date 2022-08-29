package com.lguilherme.bookstoragemanager.models.dto.PublishersDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PublisherResponseDTO {

    private Long id;

    private String name;

    private String city;
}
