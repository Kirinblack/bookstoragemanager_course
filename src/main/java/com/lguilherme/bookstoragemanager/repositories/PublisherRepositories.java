package com.lguilherme.bookstoragemanager.repositories;


import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PublisherRepositories extends JpaRepository<PublisherRepositories, Long> {

    Optional<Publisher> findByName(String name);

}
