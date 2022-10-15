package com.lguilherme.bookstoragemanager.repositories;


import com.lguilherme.bookstoragemanager.models.Entity.publisher.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface PublisherRepository extends JpaRepository<Publisher, Long> {

    Optional<Publisher> findByName(String name);

    Optional<Publisher> findById(Long id);

    Optional<Publisher> findByNameOrCode(String name , String code);

}
