package com.lguilherme.bookstoragemanager.repositories;

import com.lguilherme.bookstoragemanager.models.Entity.Users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepositories extends JpaRepository<UsersRepositories, Long> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByName(String name);
}



