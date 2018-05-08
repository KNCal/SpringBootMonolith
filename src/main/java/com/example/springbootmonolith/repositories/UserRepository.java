package com.example.springbootmonolith.repositories;

import com.example.springbootmonolith.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}