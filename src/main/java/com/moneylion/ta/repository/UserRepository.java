package com.moneylion.ta.repository;

import org.springframework.data.repository.CrudRepository;

import com.moneylion.ta.model.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends CrudRepository<User, Long> { 
    @Query("SELECT u FROM User u WHERE u.email = :email")
    User findByEmail(String email);
     
}
