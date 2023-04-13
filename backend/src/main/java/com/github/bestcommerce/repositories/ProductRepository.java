package com.github.bestcommerce.repositories;

import com.github.bestcommerce.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query(value = "SELECT obj "
            + "FROM Product obj "
            + "WHERE UPPER(obj.name) "
            + "LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<Product> searchByName(String name, Pageable pageable);
}
