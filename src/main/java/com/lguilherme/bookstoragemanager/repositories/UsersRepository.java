package com.lguilherme.bookstoragemanager.repositories;

import com.lguilherme.bookstoragemanager.models.Entity.Users.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);

    Optional<Users> findByEmail(String email);

    Optional<Users> findAllByUsername(String user);

    Optional<Users> findByUsernameAndEmail(String username, String email);


}



