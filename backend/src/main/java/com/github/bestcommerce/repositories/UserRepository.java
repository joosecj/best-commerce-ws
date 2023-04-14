package com.github.bestcommerce.repositories;

import com.github.bestcommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    @Query("SELECT u " +
            "FROM User u " +
            "WHERE u.email =:email")
    User findByUserEmail(@Param("email") String email);
}
