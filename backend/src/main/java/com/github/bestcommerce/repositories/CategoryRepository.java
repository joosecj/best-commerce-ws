package com.github.bestcommerce.repositories;

import com.github.bestcommerce.entities.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CategoryRepository  extends JpaRepository<Category, UUID> {
    @Query(value = "SELECT obj "
            + "FROM Category obj "
            + "WHERE UPPER(obj.name) "
            + "LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Category> searchByName(String name, Pageable pageable);
}
