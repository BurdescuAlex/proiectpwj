package com.example.proiect_java.Repository;


import com.example.proiect_java.Model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{
    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
}
